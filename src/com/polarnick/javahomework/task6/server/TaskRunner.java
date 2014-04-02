package com.polarnick.javahomework.task6.server;

import com.polarnick.javahomework.task6.tasks.Task;

import java.util.concurrent.ExecutionException;

/**
 * Date: 02.04.14 at 17:42
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
public interface TaskRunner {

    <X, Y> X run(Task<X, Y> task, Y value) throws InterruptedException, ExecutionException;

}
