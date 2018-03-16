package com.epam.denis_telezhenko.universityhelper.ui.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;


public class TimeUtils {

    public static Long getTimeInLong(String time) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd, HH:mm");
            Date date = formatter.parse(time);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static String getDateInString(int year, int month, int day) {
        Date date = new GregorianCalendar(year, month, day).getTime();
        return getDateInString(date);
    }

    public static String getTimeInString(int hour, int minutes) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);

        return getTimeInString(calendar.getTime());
    }

    public static String getTimeInString(Date date) {
        String time = DateFormat.getTimeInstance(SimpleDateFormat.SHORT, new Locale("ua")).format(date);
        Log.d("time", time);
        return time;
    }

    public static String getDateInString(Date date) {
        String month =
                DateFormat.getDateInstance(SimpleDateFormat.LONG, new Locale("ru")).format(date);
        Log.d("month", month);
        return month;
    }
}
