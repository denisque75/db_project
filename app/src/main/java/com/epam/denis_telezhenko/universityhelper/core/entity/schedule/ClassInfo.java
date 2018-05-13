package com.epam.denis_telezhenko.universityhelper.core.entity.schedule;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

public class ClassInfo implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String subject;
    private String type;
    private String teacher;
    private String time;
    private int parNumber;
    private int aud;
    private String dayDay;

    public ClassInfo() {
    }

    @Ignore
    public ClassInfo(int id, String subject, String type, String teacher, String time, int parNumber, int aud) {
        this.id = id;
        this.subject = subject;
        this.type = type;
        this.teacher = teacher;
        this.time = time;
        this.parNumber = parNumber;
        this.aud = aud;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getAud() {
        return aud;
    }

    public void setAud(int aud) {
        this.aud = aud;
    }

    public int getParNumber() {
        return parNumber;
    }

    public void setParNumber(int parNumber) {
        this.parNumber = parNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDayDay() {
        return dayDay;
    }

    public void setDayDay(String dayDay) {
        this.dayDay = dayDay;
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "subject='" + subject + '\'' +
                ", type='" + type + '\'' +
                ", teacher='" + teacher + '\'' +
                ", time='" + time + '\'' +
                ", parNumber='" + parNumber + '\'' +
                ", aud=" + aud +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.subject);
        dest.writeString(this.type);
        dest.writeString(this.teacher);
        dest.writeString(this.time);
        dest.writeInt(this.parNumber);
        dest.writeInt(this.aud);
        dest.writeString(this.dayDay);
    }

    protected ClassInfo(Parcel in) {
        this.id = in.readInt();
        this.subject = in.readString();
        this.type = in.readString();
        this.teacher = in.readString();
        this.time = in.readString();
        this.parNumber = in.readInt();
        this.aud = in.readInt();
        this.dayDay = in.readString();
    }

    public static final Parcelable.Creator<ClassInfo> CREATOR = new Parcelable.Creator<ClassInfo>() {
        @Override
        public ClassInfo createFromParcel(Parcel source) {
            return new ClassInfo(source);
        }

        @Override
        public ClassInfo[] newArray(int size) {
            return new ClassInfo[size];
        }
    };
}
