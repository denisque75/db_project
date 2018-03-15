package com.epam.denis_telezhenko.universityhelper.ui.utils;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
