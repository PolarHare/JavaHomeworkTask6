package com.polarnick.javahomework.task6.client;

import com.polarnick.javahomework.task6.server.TaskRunner;
import com.polarnick.javahomework.task6.tasks.Task;
import com.polarnick.javahomework.task6.tasks.TaskFactory;
import com.polarnick.javahomework.task6.utils.Utils;

import java.util.concurrent.ExecutionException;

/**
 * Date: 02.04.14 at 17:41
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
public class Client<Result, Param> implements Runnable {

    private static final boolean TO_LOG = true;

    private final int id;
    private final TaskRunner taskRunner;
    private final TaskFactory<Result, Param> taskFactory;

    public Client(int id, TaskRunner taskRunner, TaskFactory<Result, Param> taskFactory) {
        this.id = id;
        this.taskRunner = taskRunner;
        this.taskFactory = taskFactory;
    }

    @Override
    public void run() {
        boolean finished = false;
        while (!finished) {
            try {
                Task<Result, Param> task = taskFactory.createTask();
                Param argument = taskFactory.createArgument();
                Utils.log(TO_LOG, "Client " + id + ":\ttask queued with argument = " + taskFactory.toStringArgument(argument));
                Result result = taskRunner.run(task, argument);
                Utils.log(TO_LOG, "Client " + id + ":\ttask executed with result = " + taskFactory.toStringResult(result));
            } catch (InterruptedException ignored) {
                finished = true;
            } catch (ExecutionException e) {
                throw new IllegalStateException(e);
            }
        }
    }

}
