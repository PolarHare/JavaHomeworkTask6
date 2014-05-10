package com.polarnick.javahomework.task6.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Date: 02.04.14 at 17:45
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
public class ArraySortTaskFactory extends TaskFactory<List<Integer>, List<Integer>> {

    private final int maxArraySize;
    private final Random random;

    public ArraySortTaskFactory(int maxArraySize) {
        this.maxArraySize = maxArraySize;
        this.random = new Random();
    }

    @Override
    public ArraySortTask createTask() {
        return new ArraySortTask();
    }

    @Override
    public List<Integer> createArgument() {
        int n = random.nextInt(maxArraySize) + 1;
        List<Integer> a = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            a.add(random.nextInt());
        }
        return a;
    }

}
