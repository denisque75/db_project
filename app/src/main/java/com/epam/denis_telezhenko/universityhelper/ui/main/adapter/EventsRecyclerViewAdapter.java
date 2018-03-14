package com.epam.denis_telezhenko.universityhelper.ui.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.epam.denis_telezhenko.universityhelper.R;
import com.epam.denis_telezhenko.universityhelper.entity.NoteEntity;
import com.epam.denis_telezhenko.universityhelper.ui.utils.TimeUtils;

import java.util.List;

public class EventsRecyclerViewAdapter extends RecyclerView.Adapter<EventsRecyclerViewAdapter.ViewHolder> {
    private List<NoteEntity> noteEntities;
    private OnClickItem onClickItem;

    public EventsRecyclerViewAdapter(List<NoteEntity> noteEntities,OnClickItem onClickItem){
        this.noteEntities = noteEntities;
        this.onClickItem = onClickItem;
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

        void onBindView(OnClickItem onClickItem, NoteEntity note){
            title.setText(note.getTitle());
            description.setText(note.getDescrition());
            time.setText(TimeUtils.getTimeInString(note.getDate()));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickItem.clickItem(note.getId());
                }
            });
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
        return noteEntities.size();
    }

    public interface OnClickItem {

        void clickItem(long id);
    }
}
