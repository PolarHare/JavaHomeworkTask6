package com.polarnick.javahomework.task6.tasks;

import java.util.Collections;
import java.util.List;

/**
 * Date: 02.04.14 at 17:46
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
public class ArraySortTask implements Task<List<Integer>, List<Integer>> {

    public ArraySortTask() {
    }

    @Override
    public List<Integer> run(List<Integer> listToSort) {
        Collections.sort(listToSort);
        return listToSort;
    }

}