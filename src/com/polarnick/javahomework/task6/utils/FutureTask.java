package com.polarnick.javahomework.task6.utils;

/**
 * @author Polyarnyi Nikolay
 */
public class FutureTask<R> implements Runnable {

    private final Callable<R> task;
    private final Object taskExecutedLock = new Object();
    private R result;
    private Exception executionException;

    public FutureTask(Callable<R> task) {
        this.task = task;
        this.result = null;
        this.executionException = null;
    }

    @Override
    public void run() {
        synchronized (taskExecutedLock) {
            try {
                this.result = task.call();
            } catch (Exception e) {
                this.executionException = e;
            }
            taskExecutedLock.notifyAll();
        }
    }

    public R get() throws InterruptedException, ExecutionException {
        synchronized (taskExecutedLock) {
            if (this.result == null && this.executionException == null) {
                taskExecutedLock.wait();
            }
            if (this.executionException != null) {
                throw new ExecutionException(executionException);
            }
            return this.result;
        }
    }
}
