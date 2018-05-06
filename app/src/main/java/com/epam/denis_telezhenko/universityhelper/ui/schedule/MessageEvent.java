package com.epam.denis_telezhenko.universityhelper.ui.schedule;

public class MessageEvent {

    private final String message;
    private int position;

    public String getMessage() {
        return message;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public MessageEvent(String message, int position) {
        this.message = message;
        this.position = position;
    }
}
