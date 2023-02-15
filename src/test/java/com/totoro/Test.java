package com.totoro;

import java.time.Duration;
import java.time.LocalDateTime;

public class Test {
    public static void main(String[] args) throws NoSuchFieldException {


        LocalDateTime now = LocalDateTime.now();
        LocalDateTime of = LocalDateTime.of(2023, 02, 16, 5, 0);

        Duration between = Duration.between(now, of);

        int minutes = (int) between.toMinutes();


        int m = minutes % 60;
        int hour = minutes / 60;
        int day = minutes /  (60 * 24);



        System.out.println(m);
        System.out.println(hour);
        System.out.println(day);

        LocalDateTime timeLeft = LocalDateTime.of(0, 0, day, hour, m);
        System.out.println(timeLeft);
    }
}

