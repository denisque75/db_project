package com.epam.denis_telezhenko.universityhelper.ui.schedule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.epam.denis_telezhenko.universityhelper.R;

import java.util.ArrayList;
import java.util.Objects;

public class ExpScheduleAdapter extends BaseExpandableListAdapter {

    private Context scheduleContext;
    private ArrayList<Data> scheduleFirstData;
    private ArrayList<ArrayList<Data>> scheduleSecondData;

    public ExpScheduleAdapter(Context scheduleContext,ArrayList<Data> scheduleFirstData, ArrayList<ArrayList<Data>> scheduleSecondData) {
        this.scheduleSecondData = scheduleSecondData;
        this.scheduleContext = scheduleContext;
        this.scheduleFirstData = scheduleFirstData;
    }

    @Override
    public int getGroupCount() {
        return scheduleSecondData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return scheduleSecondData.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return scheduleSecondData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return scheduleSecondData.get(groupPosition).get(childPosition);
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

        if (isExpanded){
            //Изменяем что-нибудь, если текущая Group раскрыта
        }
        else{
            //Изменяем что-нибудь, если текущая Group скрыта
        }
        TextView textGroup = convertView.findViewById(R.id.schedule_main_subject_text_view);
        TextView typeLesson = convertView.findViewById(R.id.schedule_type_text_view);
        textGroup.setText(scheduleFirstData.get(groupPosition).getSubject());
        typeLesson.setText(scheduleFirstData.get(groupPosition).getType());
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
        subject.setText(scheduleSecondData.get(groupPosition).get(childPosition).getSubject());
        teacher.setText(scheduleSecondData.get(groupPosition).get(childPosition).getTeacher());
        time.setText(scheduleSecondData.get(groupPosition).get(childPosition).getTime());
        room.setText(scheduleSecondData.get(groupPosition).get(childPosition).getRoom());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
