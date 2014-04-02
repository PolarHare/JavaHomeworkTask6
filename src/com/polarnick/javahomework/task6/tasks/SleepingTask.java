package com.polarnick.javahomework.task6.tasks;

/**
 * Date: 02.04.14 at 18:41
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
public class SleepingTask implements Task<Long, Long> {

    public SleepingTask() {
    }

    @Override
    public Long run(Long toSleepMs) {
        if (toSleepMs == null) {
            throw new IllegalArgumentException("Argument toSleepMs must not be null!");
        }
        long started = System.currentTimeMillis();
        try {
            Thread.sleep(toSleepMs);
        } catch (InterruptedException ignored) {
        }
        long ended = System.currentTimeMillis();
        return ended - started;
    }

}