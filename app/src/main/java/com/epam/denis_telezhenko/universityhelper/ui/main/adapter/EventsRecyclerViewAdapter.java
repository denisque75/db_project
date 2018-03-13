package com.epam.denis_telezhenko.universityhelper.ui.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epam.denis_telezhenko.universityhelper.R;
import com.epam.denis_telezhenko.universityhelper.entity.NoteEntity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EventsRecyclerViewAdapter extends RecyclerView.Adapter<EventsRecyclerViewAdapter.ViewHolder> {
    private List<NoteEntity> noteEntities;

    public EventsRecyclerViewAdapter(List<NoteEntity> noteEntities){
        this.noteEntities = noteEntities;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView description;
        TextView time;

        ViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.main_card_item__title);
            description = itemView.findViewById(R.id.main_card_item__desc);
            time = itemView.findViewById(R.id.main_card_item__time);
        }

        void onBindView(NoteEntity note){
            title.setText(note.getTitle());
            description.setText(note.getDescrition());
            time.setText(convertLongToDate(note.getDate()));
        }

        private String convertLongToDate(Date date) {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm a");
            return formatter.format(date);
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_activity_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBindView(noteEntities.get(position));
    }

    @Override
    public int getItemCount() {
        return noteEntities.size();
    }
}
