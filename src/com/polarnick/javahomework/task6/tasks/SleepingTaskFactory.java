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
        this(maxTimeToSleepMs, 239);
    }

    public SleepingTaskFactory(int maxTimeToSleepMs, int seed) {
        this.maxTimeToSleepMs = maxTimeToSleepMs;
        this.random = new Random(seed);
    }

    @Override
    public SleepingTask createTask() {
        return new SleepingTask();
    }

    @Override
    public Long createArgument() {
        return (long) (random.nextInt(maxTimeToSleepMs) + 1);
    }

    @Override
    public String toStringResult(Long result) {
        return result.toString();
    }

    @Override
    public String toStringArgument(Long argument) {
        return argument.toString();
    }

}
