package com.denis_telezhenko.universityhelper.core.entity;

public class User {
    private String email;
    private boolean isAdmin;
    private String group;
    private String uid;
    private long noteId;

    public User() {
    }

    public User(String email, String uid) {
        this.email = email;
        this.uid = uid;
    }

    public User(String email, boolean isAdmin, String group, String uid, long noteId) {
        this.email = email;
        this.isAdmin = isAdmin;
        this.group = group;
        this.uid = uid;
        this.noteId = noteId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public long getNoteId() {
        return noteId;
    }

    public void setNoteId(long noteId) {
        this.noteId = noteId;
    }
}
