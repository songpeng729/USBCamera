package com.finger.usbcamera.util;

import java.sql.Timestamp;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static String date2String(Date date) {
        return date2String(date, "yyyy-MM-dd");
    }
    public static String date2TimeString(Date date) {
        return date2String(date, "HH:mm:ss");
    }
    public static String date2DateTimeString(Date date) {
        return date2String(date, "yyyy-MM-dd HH:mm:ss");
    }

    public static String date2String(Date date, String format) {
        String dateString = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            dateString = formatter.format(date);
        } catch (Exception e) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                dateString = formatter.format(date);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        return dateString;
    }

    public static Date dateString2Date(String strDate) {
        return string2Date(strDate, "yyyy-MM-dd");
    }
    public static Date dateTimeString2Date(String strDate) {
        return string2Date(strDate, "yyyy-MM-dd HH:mm:ss");
    }
    public static Date string2Date(String strDate, String format) {
        Date date = null;
        ParsePosition pos = new ParsePosition(0);
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            date = formatter.parse(strDate, pos);
        } catch (Exception e) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                date = formatter.parse(strDate);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return date;
    }

}
