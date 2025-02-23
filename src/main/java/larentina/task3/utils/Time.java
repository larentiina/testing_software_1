package larentina.task3.utils;

import lombok.Getter;

public class Time {
    @Getter
    private static volatile int currentTime = 0;

    public static void tick() {
        currentTime++;
    }

    public static void reset() {
        currentTime = 0;
    }
}