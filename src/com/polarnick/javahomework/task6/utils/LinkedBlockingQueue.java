package com.polarnick.javahomework.task6.utils;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author Polyarnyi Nikolay
 */
public class LinkedBlockingQueue<E> {

    private final Deque<E> queue;

    public LinkedBlockingQueue() {
        queue = new LinkedList<>();
    }

    public E take() throws InterruptedException {
        E result;
        synchronized (queue) {
            while (queue.isEmpty()) {
                queue.wait();
            }
            result = queue.removeFirst();
        }
        return result;
    }

    public void put(E element) {
        synchronized (queue) {
            queue.addLast(element);
            queue.notify();
        }
    }

}
