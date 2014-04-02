package com.polarnick.javahomework.task6.tasks;

import java.util.Arrays;

/**
 * Date: 02.04.14 at 17:46
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
public class ArraySortTask implements Task<int[], int[]> {

    public ArraySortTask() {
    }

    @Override
    public int[] run(int[] value) {
        Arrays.sort(value);
        return value;
    }

}