package com.epam.denis_telezhenko.universityhelper.ui.schedule;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Data implements Serializable {

    private String subject;
    private String type;
    private String teacher;
    private String time;
    private String room;
    private int id;

    public Data() {
    }

    public Data(String subject, String type, int id) {
        this.subject = subject;
        this.type = type;
        this.id = id;
    }

    public Data(String subject, String teacher, String time, String room, int id, String type) {
        this.subject = subject;
        this.teacher = teacher;
        this.time = time;
        this.room = room;
        this.id = id;
        this.type = type;
    }

    public Data(String subject, String teacher, String time, String room, int id) {
        this.subject = subject;
        this.teacher = teacher;
        this.time = time;
        this.room = room;
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getTime() {
        return time;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
