package com.epam.denis_telezhenko.universityhelper.core.services.firebase;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.epam.denis_telezhenko.universityhelper.core.entity.schedule.Data;
import com.epam.denis_telezhenko.universityhelper.core.entity.Note;
import com.epam.denis_telezhenko.universityhelper.core.entity.schedule.Day;
import com.epam.denis_telezhenko.universityhelper.core.entity.schedule.ClassInfo;
import com.epam.denis_telezhenko.universityhelper.core.firebase_callbacks.DataFromDBCallback;
import com.epam.denis_telezhenko.universityhelper.ui.utils.Constants;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ReadFromFirebase {

    private static final String TAG = "ReadFromFirebase";

    public static void readNotes(DataFromDBCallback<Note> callback,
                                 DatabaseReference database, String uid, String group) {
        ValueEventListener noteListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Note> noteList = new ArrayList<>();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Log.d("FirebaseNote", data.toString());
                    noteList.add(data.getValue(Note.class));
                }
                callback.newDataFromServer(noteList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        database.child(Constants.NOTES_NODE).child(uid).addValueEventListener(noteListener);
        database.child(Constants.NOTES_NODE).child(group).addListenerForSingleValueEvent(noteListener);
    }

    public static void readSchedule(MutableLiveData<List<Data>> scheduleData,
                                    DatabaseReference database, String group) {
        ValueEventListener scheduleListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Data> schedule = new ArrayList<>();

                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Data scheduleData = new Data();
                    scheduleData.setGroup(group);

                    String dayName = getDateName(data.getKey());
                    Log.d(TAG, "onDataChange: day = " + dayName);

                    scheduleData.setDayName(dayName);

                    Day day = new Day();
                    day.setDay(dayName);

                    List<ClassInfo> classInfoList = new ArrayList<>();
                    for (DataSnapshot numbOfPair : data.getChildren()) {
                        ClassInfo classInfo = numbOfPair.getValue(ClassInfo.class);
                        assert classInfo != null;
                        classInfo.setSubject((String) numbOfPair.child("class").getValue());
                        Log.d(TAG, "onDataChange: ClassInfo: " + classInfo);
                        classInfo.setParNumber(Integer.valueOf(numbOfPair.getKey()));
                        classInfoList.add(classInfo);
                    }
                    day.setClassInfos(classInfoList);

                    scheduleData.setDay(day);
                    schedule.add(scheduleData);
                    Log.d(TAG, "onDataChange: Data: " + scheduleData);
                }
                scheduleData.setValue(schedule);
            }

            private String getDateName(String key) {
                switch (key) {
                    case "1" : return "Понедельник";
                    case "2" : return "Вторник";
                    case "3" : return "Среда";
                    case "4" : return "Четверг";
                    case "5" : return "Пятница";
                    default: return "Суббота";
                }
            }

            private void addToList(List<ClassInfo> classInfoList,int index, ClassInfo classInfo) {
                if (index >= classInfoList.size()) {
                    classInfoList.add(classInfo);
                } else {
                    classInfoList.add(index, classInfo);
                }
            }

            private int getIndex(String key) {
                switch (key) {
                    case "First" : return 1;
                    case "Second" : return 2;
                    case "Third" : return 3;
                    case "Fourth" : return 4;
                    case "Fifth" : return 5;
                    default: return 6;

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "loadPost:onCancelled", databaseError.toException());
                scheduleData.setValue(null);
            }
        };
        database
                .child(Constants.SCHEDULE_NODE)
                .child(group)
                .addValueEventListener(scheduleListener);
    }

}
