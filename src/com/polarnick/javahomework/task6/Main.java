package com.polarnick.javahomework.task6;

import com.polarnick.javahomework.task6.client.Client;
import com.polarnick.javahomework.task6.server.TaskRunnerDaemon;
import com.polarnick.javahomework.task6.tasks.SleepingTaskFactory;
import com.polarnick.javahomework.task6.tasks.TaskFactory;

/**
 * Date: 02.04.14 at 18:32
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
public class Main {

    private static final int RUNNERS_COUNT = 4;
    private static final int RUNNER_THREADS_COUNT = 4;
    private static final int CLIENTS_PER_RUNNER_COUNT = 4;

    public static void main(String[] args) {
        startRunnersWithClients(new SleepingTaskFactory(6000));
    }

    private static void startRunnersWithClients(TaskFactory<?, ?> taskFactory) {
        for (int i = 0; i < RUNNERS_COUNT; i++) {
            TaskRunnerDaemon runner = startTaskRunner(i);
            for (int j = 0; j < CLIENTS_PER_RUNNER_COUNT; j++) {
                startClient(i * CLIENTS_PER_RUNNER_COUNT + j, taskFactory, i, runner);
            }
        }
    }

    private static TaskRunnerDaemon startTaskRunner(int id) {
        TaskRunnerDaemon runner = new TaskRunnerDaemon(id, RUNNER_THREADS_COUNT);
        runner.start();
        return runner;
    }

    private static void startClient(int id, TaskFactory<?, ?> taskFactory, int runnerId, TaskRunnerDaemon runner) {
        Client client = new Client<>(id, runner, taskFactory);
        Thread clientThread = new Thread(client, "Client with id=" + id + " for runner with id=" + runnerId);
        clientThread.start();
    }

}
