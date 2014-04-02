package com.polarnick.javahomework.task6.tasks;

import java.util.Arrays;
import java.util.Random;

/**
 * Date: 02.04.14 at 17:45
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
public class ArraySortTaskFactory extends TaskFactory<int[], int[]> {

    private final int maxArraySize;
    private final Random random;

    public ArraySortTaskFactory(int maxArraySize) {
        this(maxArraySize, 239);
    }

    public ArraySortTaskFactory(int maxArraySize, int seed) {
        this.maxArraySize = maxArraySize;
        this.random = new Random(seed);
    }

    @Override
    public ArraySortTask createTask() {
        return new ArraySortTask();
    }

    @Override
    public int[] createArgument() {
        int n = random.nextInt(maxArraySize) + 1;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = random.nextInt();
        }
        return a;
    }

    @Override
    public String toStringResult(int[] result) {
        return Arrays.toString(result);
    }

    @Override
    public String toStringArgument(int[] argument) {
        return Arrays.toString(argument);
    }

}
