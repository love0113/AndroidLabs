package com.example.androidlabs;

public class Message {

    private long id;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSent() {
        return isSent;
    }

    public void setSent(boolean sent) {
        isSent = sent;
    }



    private String message;
    private boolean isSent;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Message(long id, String message, boolean isSent)
    {
        this.id = id;
        this.message = message;
        this.isSent = isSent;

    }

}