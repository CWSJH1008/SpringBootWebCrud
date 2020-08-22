package com.gd.test.config;

import org.aspectj.lang.annotation.Aspect;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2020/1/12.
 */

public class TestDate {

    private static Date date;
    private static SimpleDateFormat simpleDateFormat;
    private static String format;
    private static Calendar instance;

    public static void main(String[] args) {

        date = new Date();
        System.out.println(date);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("hellotT");
        format = simpleDateFormat.format(date);
        System.out.println(format);
        instance = Calendar.getInstance();


    }
}
