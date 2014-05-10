package com.polarnick.javahomework.task6.tasks;

import java.util.Random;

/**
 * Date: 02.04.14 at 18:41
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
public class SleepingTaskFactory extends TaskFactory<Long, Long> {

    private final int maxTimeToSleepMs;
    private final Random random;

    public SleepingTaskFactory(int maxTimeToSleepMs) {
        this.maxTimeToSleepMs = maxTimeToSleepMs;
        this.random = new Random();
    }

    @Override
    public SleepingTask createTask() {
        return new SleepingTask();
    }

    @Override
    public Long createArgument() {
        return (long) (random.nextInt(maxTimeToSleepMs) + 1);
    }

}
