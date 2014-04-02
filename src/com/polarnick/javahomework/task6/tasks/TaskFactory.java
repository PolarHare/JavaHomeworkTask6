package com.polarnick.javahomework.task6.tasks;

/**
 * Date: 02.04.14 at 17:44
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
public abstract class TaskFactory<Result, Param> {

    public abstract Task<Result, Param> createTask();

    public abstract Param createArgument();

}
