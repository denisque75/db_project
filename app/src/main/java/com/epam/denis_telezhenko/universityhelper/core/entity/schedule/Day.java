package com.epam.denis_telezhenko.universityhelper.core.entity.schedule;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Day implements Parcelable {

    @PrimaryKey
    @NonNull
    private String day;

    @Ignore
    private List<ClassInfo> classInfos;

    public Day() {
    }

    @Ignore
    public Day(@NonNull String day, List<ClassInfo> classInfos) {
        this.day = day;
        this.classInfos = classInfos;
    }

    @NonNull
    public String getDay() {
        return day;
    }

    public void setDay(@NonNull String day) {
        this.day = day;
    }

    public List<ClassInfo> getClassInfos() {
        return classInfos;
    }

    public void setClassInfos(List<ClassInfo> classInfos) {
        this.classInfos = classInfos;
    }

    @Override
    public String toString() {
        return "Day{" +
                "day='" + day + '\'' +
                ", classInfos=" + classInfos +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.day);
        dest.writeList(this.classInfos);
    }

    protected Day(Parcel in) {
        this.day = in.readString();
        this.classInfos = new ArrayList<ClassInfo>();
        in.readList(this.classInfos, ClassInfo.class.getClassLoader());
    }

    public static final Parcelable.Creator<Day> CREATOR = new Parcelable.Creator<Day>() {
        @Override
        public Day createFromParcel(Parcel source) {
            return new Day(source);
        }

        @Override
        public Day[] newArray(int size) {
            return new Day[size];
        }
    };
}
