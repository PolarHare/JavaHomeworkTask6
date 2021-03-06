package com.polarnick.javahomework.task6.server;

import com.polarnick.javahomework.task6.tasks.Task;

/**
 * Date: 02.04.14 at 17:42
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
public interface TaskRunner {

    /**
     * Runs the given {@code task}, and returns result of running given {@code task} with given argument {@code value}.
     *
     * @param task  task to be executed. Must not be {@code null}
     * @param value argument for {@code task}
     * @param <R>   type of {@code task} result
     * @param <V>   type of {@code task} argument
     * @return result of task executed with given {@code value}
     * @throws NullPointerException           if {@code task} is null
     * @throws java.lang.InterruptedException if execution was interrupted
     */
    <R, V> R run(Task<R, V> task, V value) throws InterruptedException;

}
