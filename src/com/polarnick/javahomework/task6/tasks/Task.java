package com.polarnick.javahomework.task6.tasks;

/**
 * Date: 02.04.14 at 17:42
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
public interface Task<Result, Param> {

    Result run(Param value);

}