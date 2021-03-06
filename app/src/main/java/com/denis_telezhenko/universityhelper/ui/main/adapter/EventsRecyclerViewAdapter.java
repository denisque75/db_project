package com.denis_telezhenko.universityhelper.ui.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.denis_telezhenko.universityhelper.R;
import com.denis_telezhenko.universityhelper.core.entity.Note;
import com.denis_telezhenko.universityhelper.ui.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

public class EventsRecyclerViewAdapter extends RecyclerView.Adapter<EventsRecyclerViewAdapter.ViewHolder> {
    private List<Note> noteEntities;
    private OnClickItem onClickItem;

    public EventsRecyclerViewAdapter(List<Note> noteEntities, OnClickItem onClickItem){
        this.noteEntities = noteEntities;
        this.onClickItem = onClickItem;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        private static final String TAG = "RecyclerViewMainAdapter";
        TextView title;
        TextView description;
        TextView time;
        ImageView adminLabel;

        ViewHolder(View itemView){
            super(itemView);
            title = itemView.findViewById(R.id.main_card_item__title);
            description = itemView.findViewById(R.id.main_card_item__desc);
            time = itemView.findViewById(R.id.main_card_item__time);
            adminLabel = itemView.findViewById(R.id.create_note__admin_label);
        }

        void onBindView(OnClickItem onClickItem, Note note){
            if (note.getDate() == null) {
                LinearLayout layout = itemView.findViewById(R.id.main_card_item__time_linearLayout);
                LinearLayout.LayoutParams params =
                        new LinearLayout.LayoutParams(0, 0);
                params.weight = 0;
                layout.setLayoutParams(params);
            }
            if (note.isGlobal()) {
                adminLabel.setVisibility(View.VISIBLE);
            }
            title.setText(note.getTitle());
            description.setText(note.getDescription());
            time.setText(TimeUtils.getTimeInString(note.getDate()));
            Log.d(TAG, "onBindView: " + note);
            itemView.setOnClickListener(v -> onClickItem.clickItem(note.getId()));
        }

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_activity_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.onBindView(onClickItem, noteEntities.get(position));
    }

    @Override
    public int getItemCount() {
        if (noteEntities == null) {
            return 0;
        }
        return noteEntities.size();
    }

    public void addNote(Note note) {
        if (noteEntities == null) {
            noteEntities = new ArrayList<>();
        }
        noteEntities.add(note);
        notifyItemChanged(noteEntities.size() - 1);
    }

    public void setNoteEntities(List<Note> noteEntities) {
        this.noteEntities = noteEntities;
        notifyDataSetChanged();
    }

    public interface OnClickItem {

        void clickItem(long id);
    }
}
