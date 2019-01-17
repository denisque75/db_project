package com.denis_telezhenko.universityhelper.core.entity.schedule;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity
public class Data implements Parcelable {

    @PrimaryKey
    @NonNull
    private String group;
    @NonNull
    private String dayName;

    @Ignore
    @Embedded
    private Day day;

    public Data() {
    }

    @Ignore
    public Data(@NonNull String group, @NonNull String dayName) {
        this.group = group;
        this.dayName = dayName;
    }

    @NonNull
    public String getGroup() {
        return group;
    }

    public void setGroup(@NonNull String group) {
        this.group = group;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Data{" +
                "group='" + group + '\'' +
                ", dayName='" + dayName + '\'' +
                ", day=" + day +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.group);
        dest.writeString(this.dayName);
        dest.writeParcelable(this.day, flags);
    }

    protected Data(Parcel in) {
        this.group = in.readString();
        this.dayName = in.readString();
        this.day = in.readParcelable(Day.class.getClassLoader());
    }

    public static final Parcelable.Creator<Data> CREATOR = new Parcelable.Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel source) {
            return new Data(source);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
}
