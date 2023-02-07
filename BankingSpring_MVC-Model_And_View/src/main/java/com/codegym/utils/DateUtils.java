package com.codegym.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    private static final DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy ss:mm:hh");
    public static String dateToString(Date date) {
        return dateFormat.format(date);
    }

    public static Date stringToDate(String date) throws ParseException {
        return dateFormat.parse(date);
    }
}
