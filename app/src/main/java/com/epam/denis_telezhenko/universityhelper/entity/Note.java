package com.epam.denis_telezhenko.universityhelper.entity;

import java.util.Date;

public class Note {
    private long id;
    private String title;
    private String group;
    private String description;
    private Date date; //if date == null it means that it isn't remind

    public Note() {
    }

    public Note(String title, String group, String description) {
        this.title = title;
        this.group = group;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
