package com.polarnick.javahomework.task6.tasks;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Date: 02.04.14 at 18:41
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
public class SleepingTaskFactory extends TaskFactory<Long, Long> {

    private final int maxTimeToSleepMs;

    public SleepingTaskFactory(int maxTimeToSleepMs) {
        this.maxTimeToSleepMs = maxTimeToSleepMs;
    }

    @Override
    public SleepingTask createTask() {
        return new SleepingTask();
    }

    @Override
    public Long createArgument() {
        return (long) (ThreadLocalRandom.current().nextInt(maxTimeToSleepMs) + 1);
    }

}
