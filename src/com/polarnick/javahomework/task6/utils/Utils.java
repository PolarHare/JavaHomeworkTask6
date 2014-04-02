package com.polarnick.javahomework.task6.utils;

/**
 * Date: 02.04.14 at 19:10
 *
 * @author Nickolay Polyarniy aka PolarNick
 */
public class Utils {

    private static final int MAX_MESSAGE_LENGTH = 128;
    private static final String CUTTED_STRING_SUFFIX = "...";

    private Utils() {
    }

    public static void log(boolean toLog, String message) {
        if (toLog) {
            synchronized (System.out) {
                if (message.length() > MAX_MESSAGE_LENGTH) {
                    System.out.println(System.currentTimeMillis() + ": " + message.substring(0, MAX_MESSAGE_LENGTH - CUTTED_STRING_SUFFIX.length()) + CUTTED_STRING_SUFFIX);
                } else {
                    System.out.println(System.currentTimeMillis() + ": " + message);
                }
            }
        }
    }

}
