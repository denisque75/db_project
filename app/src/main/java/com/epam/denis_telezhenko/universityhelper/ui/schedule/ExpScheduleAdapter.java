package com.epam.denis_telezhenko.universityhelper.ui.schedule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.epam.denis_telezhenko.universityhelper.R;
import com.epam.denis_telezhenko.universityhelper.core.entity.schedule.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExpScheduleAdapter extends BaseExpandableListAdapter {
    private static final String TAG = "ExpScheduleAdapter";

    private Context scheduleContext;//todo udalit nahui
    private Data schedule;

    public ExpScheduleAdapter(Context scheduleContext,Data schedule) {
        this.schedule = schedule;

        this.scheduleContext = scheduleContext;
    }

    @Override
    public int getGroupCount() {
        Log.d(TAG, "getGroupCount: " + schedule.getDay().getClassInfos().size());
        return schedule.getDay().getClassInfos().size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Log.d(TAG, "getChildrenCount: " + 1);
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        //return scheduleSecondData.get(groupPosition);
        return schedule.getDay();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        //return scheduleSecondData.get(groupPosition).get(childPosition);
        return schedule.getDay().getClassInfos();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) scheduleContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.schedule_custom_group_view, null);
        }

        if (schedule != null) {
            TextView textGroup = convertView.findViewById(R.id.schedule_main_subject_text_view);
            TextView typeLesson = convertView.findViewById(R.id.schedule_type_text_view);
            int classNumber = schedule.getDay().getClassInfos().get(groupPosition).getParNumber();
            textGroup.setText(new StringBuilder().append("Пара ").append(classNumber).toString());
            typeLesson.setText(schedule.getDay().getClassInfos().get(groupPosition).getType());
        }
        return convertView;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) scheduleContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = Objects.requireNonNull(inflater).inflate(R.layout.schedule_custom_child_view, null);
        }

        TextView subject = convertView.findViewById(R.id.schedule_subject_text_view);
        TextView teacher = convertView.findViewById(R.id.schedule_teacher_text_view);
        TextView time = convertView.findViewById(R.id.schedule_time_text_view);
        TextView room = convertView.findViewById(R.id.schedule_room_text_view);
        subject.setText(schedule.getDay().getClassInfos().get(groupPosition).getSubject());
        teacher.setText(schedule.getDay().getClassInfos().get(groupPosition).getTeacher());
        time.setText(schedule.getDay().getClassInfos().get(groupPosition).getTime());
        room.setText(""+schedule.getDay().getClassInfos().get(groupPosition).getAud());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
