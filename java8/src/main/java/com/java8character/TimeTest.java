package com.java8character;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TimeTest {

    public static void tsetTime() throws InterruptedException {
        /*
            LocalDate  创建日期
            LocalTime  创建时间
            LocalDateTime  创建日期和时间

            现在的数据库也已经支持这些数据类型了
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
        将月份天数、年份天数、月份年份修改为指定的值并返回新的 LocalDate 对象
         */
        LocalDateTime localDateTime2 = now.withYear(2010);
        System.out.println(localDateTime2);

        //比较两个时间
        boolean after = now.isAfter(localDateTime2);
        System.out.println(after);
        boolean before = now.isBefore(localDateTime2);
        System.out.println(before);

        /*
        总结:
            LocalDate LocalTime LocalDateTime  获取的时间都是北京时间不用增加偏移量
         */






        /*

        时间戳 Instant (以Unix1970年1月1日0时0分0秒 到现在的毫秒值叫时间戳)

         */

        //默认是utc时区与北京时间查了8个小时
        Instant instant = Instant.now();
        System.out.println("默认是utc时区 instant = " + instant);
        //获取时间戳的话就不用加偏移量
        System.out.println(instant.toEpochMilli());
        System.out.println(new Date().getTime());
        //如果要算北京时间需要加8个小时的偏移量
        LocalDateTime localDateTime4 = instant.atOffset(ZoneOffset.ofHours(8)).toLocalDateTime();
        System.out.println("北京时间,时间戳"+localDateTime4);





        /*
            将LocalDate LocalTime LocalDateTime 转换成时间戳
         */

        //获取秒数 //可以直接获取秒然后再增加8个时区的偏移量
        long second = LocalDateTime.now().toEpochSecond(ZoneOffset.ofHours(8));
        System.out.println("获取当前秒数---second = " + second);
        //获取毫秒数 用toInstant()方法转换成时间戳之后再增加8个时区的偏移量
        long Milli = LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
        System.out.println("获取当前时间戳----Milli = " + Milli);


        /*
            将时间戳转时间
         */

        LocalDateTime offsetDateTime = Instant.now().atOffset(ZoneOffset.ofHours(8)).toLocalDateTime();
        System.out.println("offsetDateTime = " + offsetDateTime);



        /*
            字符串时间戳转时间
         */
        Instant instant1 = Instant.ofEpochMilli(new Date().getTime());
        LocalDateTime localDateTime3 = instant1.atOffset(ZoneOffset.ofHours(8)).toLocalDateTime();
        System.out.println("localDateTime3 = " + localDateTime3);


        /*
            LocalDateTime与String 互相转化
         */

        // LocalDateTime与String
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        String format = LocalDateTime.now().format(dateTimeFormatter);
        System.out.println("当前时间-format = " + format);

        //String 转LocalDateTime
        String localTime = "2019-10-12 16:50:22 222";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        LocalDateTime parse = LocalDateTime.parse(localTime, formatter);
        System.out.println("字符转时间-parse = " + parse);

        /*
            Date 与 LocalDateTime 互相转化
         */

        Date date = new Date();
        LocalDateTime localDateTime5 = date.toInstant().atOffset(ZoneOffset.ofHours(8)).toLocalDateTime();
        System.out.println("localDateTime5 = " + localDateTime5);

        Date from = Date.from(LocalDateTime.now().toInstant(ZoneOffset.ofHours(8)));
        System.out.println("from = " + from);

        /*
            Duration 计算两个"时间"之间的间隔,
            Period 计算俩个"日期"之间的间隔,
         */

        Instant now3 = Instant.now();
        Thread.sleep(1000);
        Instant now4 = Instant.now();
        Duration between = Duration.between(now3,now4);
        long l = between.toMillis();
        System.out.println("l = " + l);
        //一样适用
        LocalDateTime now5 = LocalDateTime.now();
        System.out.println("-------------------");

        //因为是日期不是最少单位是天,,没有毫秒值
        LocalDate now6 = LocalDate.now();
        Thread.sleep(1000);
        LocalDate now7 = LocalDate.now();
        Period between1 = Period.between(now6, now7);
        long l1 = between1.toTotalMonths();
        System.out.println("l1 = " + l1);

    }

    public static void main(String[] args) throws InterruptedException {
        tsetTime();

    }
}
