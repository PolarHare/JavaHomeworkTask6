package com.polarnick.javahomework.task6.server;

        import com.polarnick.javahomework.task6.tasks.Task;
        import com.polarnick.javahomework.task6.utils.Utils;

        import java.util.concurrent.*;

/**
 * Date: 02.04.14 at 17:42
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
public class TaskRunnerImpl implements TaskRunner, Runnable {

    private static final boolean TO_LOG = true;

    private final int id;
    private final BlockingQueue<FutureTask<?>> taskQueue;

    public TaskRunnerImpl(int id) {
        this.id = id;
        taskQueue = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        boolean finished = false;
        while (!finished) {
            try {
                FutureTask<?> task = taskQueue.take();
                task.run();
            } catch (InterruptedException e) {
                finished = true;
            }
        }
    }

    /**
     * Runs the given {@code task}, and returns result of running given {@code task} with given argument {@code value}.
     *
     * @param task  task to be runned
     * @param value argument for {@code task}
     * @param <R>   type of {@code task} result
     * @param <V>   type of {@code task} argument
     * @return result of task executed with given {@code value}
     * @throws NullPointerException if {@code task} is null
     * @throws ExecutionException   if the computation threw an exception
     */
    @Override
    public <R, V> R run(Task<R, V> task, V value) throws InterruptedException, ExecutionException {
        if (task == null) {
            throw new NullPointerException();
        }
        FutureTask<R> futureTask = new FutureTask<>(new TaskWithArgument<>(task, value));
        taskQueue.put(futureTask);
        try {
            return futureTask.get();
        } catch (ExecutionException e) {
            throw new IllegalStateException("Exception occurred, while were executing task!", e);
        }
    }

    private class TaskWithArgument<R, V> implements Callable<R> {

        private Task<R, V> task;
        private V value;

        private TaskWithArgument(Task<R, V> task, V value) {
            this.task = task;
            this.value = value;
        }

        @Override
        public R call() throws Exception {
            Utils.log(TO_LOG, "Runner " + id + ":\ttask with argument = " + value + " executing...");
            R result = task.run(value);
            Utils.log(TO_LOG, "Runner " + id + ":\ttask executed with result = " + result + "!");
            return result;
        }

    }

}
