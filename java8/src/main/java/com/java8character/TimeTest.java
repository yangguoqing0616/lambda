package com.java8character;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class TimeTest {

    public static void tsetTime() {
        /*
            LocalDate  创建日期
            LocalTime  创建时间
            LocalDateTime  创建日期和时间

         */

        //创建时间 1
        LocalDate now1 = LocalDate.now();
        System.out.println(now1);
        System.out.println("-----------------------");

        LocalTime now2 = LocalTime.now();
        System.out.println(now2);
        System.out.println("-----------------------");

        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        System.out.println("-----------------------");

        //创建一个指定的时间
        LocalDateTime of = LocalDateTime.of(2019, 10, 11, 18, 21, 38, 222);
        System.out.println(of);
        //对当前时间进行各种加的操作 plus**
        of.plusYears(3);
        LocalDateTime localDateTime = of.plusMonths(3);
        System.out.println(localDateTime);

        //对当前时间进行各种减的操作 minus**
        LocalDateTime localDateTime1 = of.minusYears(2);
        System.out.println(localDateTime1);

        //获取时间对象的各个时间部分 get**
        int year = now.getYear();
        int minute = now.getMinute();

        /*
        withDayOfMonth,
        withDayOfYear,
        withMonth,
        withYear
        ****
        将月份天数、年份天数、月份、年 份 修 改 为 指 定 的 值 并 返 回 新 的
        LocalDate 对象
         */

        LocalDateTime localDateTime2 = now.withYear(2010);
        System.out.println(localDateTime2);

        //比较两个时间
        boolean after = now.isAfter(localDateTime2);
        System.out.println(after);
        boolean before = now.isBefore(localDateTime2);
        System.out.println(before);



    }

    public static void main(String[] args) {
        tsetTime();

    }
}
