package com.epam.denis_telezhenko.universityhelper.entity;

import java.util.Date;

public class NoteEntity {
    private long id;
    private String title;
    private String descrition;
    private Date date; //if date == null it means that it isn't reminder

    public NoteEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescrition() {
        return descrition;
    }

    public void setDescrition(String descrition) {
        this.descrition = descrition;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "NoteEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", descrition='" + descrition + '\'' +
                ", date=" + date +
                '}';
    }
}
