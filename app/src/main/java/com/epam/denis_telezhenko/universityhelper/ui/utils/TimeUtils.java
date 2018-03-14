package com.epam.denis_telezhenko.universityhelper.ui.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Denis_Telezhenko on 3/14/2018.
 */

public class TimeUtils {

    public static Long getTimeInLong(String time) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm a");
            Date date = formatter.parse(time);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public static String getTimeInString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm a");
        return formatter.format(date);
    }
}
