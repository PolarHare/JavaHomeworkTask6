package com.polarnick.javahomework.task6.server;

import com.polarnick.javahomework.task6.tasks.Task;
import com.polarnick.javahomework.task6.utils.Utils;

import java.util.concurrent.*;

/**
 * Date: 02.04.14 at 17:42
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
public class TaskRunnerDaemon implements TaskRunner {

    private static final boolean TO_LOG = true;

    private final int id;
    private final int threadsCount;
    private final BlockingQueue<FutureTask<?>> taskQueue;

    private volatile boolean alreadyStarted = false;

    /**
     * @param id           identifier of daemon
     * @param threadsCount count of threads to use after {@link #start()} call
     * @see #start()
     */
    public TaskRunnerDaemon(int id, int threadsCount) {
        this.id = id;
        this.threadsCount = threadsCount;
        this.taskQueue = new LinkedBlockingQueue<>();
    }

    private class Worker implements Runnable {

        private final int id;

        private Worker(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    FutureTask<?> task = taskQueue.take();
                    task.run();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

    }

    /**
     * Starts daemon to work in passed via constructor {@code threadsCount} threads.
     * <p/>
     * It will execute even those tasks, that were runned via {@link #run(com.polarnick.javahomework.task6.tasks.Task, Object)}
     * before starting.
     * <p/>
     * It is never legal to start a TaskRunnerDaemon more than once.
     *
     * @throws IllegalStateException if the TaskRunnerDaemon was already started.
     */
    public synchronized void start() {
        if (alreadyStarted) {
            throw new IllegalStateException("This " + TaskRunnerDaemon.class.getSimpleName() + " already started!");
        }
        alreadyStarted = true;
        for (int i = 0; i < threadsCount; i++) {
            Thread thread = new Thread(new Worker(i), "TaskRunnerDaemon id=" + id + " workerId=" + i);
            thread.setDaemon(true);
            thread.start();
        }
    }

    @Override
    public <R, V> R run(Task<R, V> task, V value) throws InterruptedException {
        if (task == null) {
            throw new NullPointerException();
        }
        FutureTask<R> futureTask = new FutureTask<>(new DaemonTaskWithArgument<>(task, value));
        taskQueue.put(futureTask);
        try {
            return futureTask.get();
        } catch (ExecutionException e) {
            throw new IllegalStateException("Exception occurred, while were executing task!", e);
        }
    }

    private class DaemonTaskWithArgument<R, V> implements Callable<R> {

        private Task<R, V> task;
        private V value;

        private DaemonTaskWithArgument(Task<R, V> task, V value) {
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
