package com.denis_telezhenko.universityhelper.ui.utils;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils{

    private int dayPos;

    public DateUtils(int dayPos) {
        this.dayPos = dayPos;
    }

    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat getDateFormat(){
        return new SimpleDateFormat("EEEE  dd.MM.yyyy ");
    }

    @NonNull
    private Date getCurrentDate(){
        return new Date();
    }

    @NonNull
    private Calendar getCalendar(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getCurrentDate());
        calendar.add(Calendar.DAY_OF_YEAR, dayPos);
        return calendar;
    }

    @NonNull
    private Date getFixedDate(){
        return getCalendar().getTime();
    }

    @NonNull
    private String getCurrentDateStr(){
        return getDateFormat().format(getFixedDate());
    }

    private String getRefactorDate(){
        StringBuilder res = new StringBuilder();
        String [] str = getCurrentDateStr().split("");
        res.append(str[1].toUpperCase());
        for (int i = 2; i < str.length - 1; i++){
            res.append(str[i]);
        }
        return res.toString();
    }

    public String getResultDate(){
        return getRefactorDate();
    }

}
