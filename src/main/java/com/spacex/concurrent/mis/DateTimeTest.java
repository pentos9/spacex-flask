package com.spacex.concurrent.mis;

import java.util.Calendar;
import java.util.Date;

public class DateTimeTest {
    public static void main(String[] args) {
        System.out.println(new Date(657388800L));

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();
        System.out.println(date+ "|" + calendar.getTimeInMillis());
    }
}
