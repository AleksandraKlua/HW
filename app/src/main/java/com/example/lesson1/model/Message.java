package com.example.lesson1.model;

public class Message {
    private int label; //0 - interlocuter's message, 1 - user's message
    private String messageText;

    public void setMessageLabel(int label){
        this.label = label;
    }

    public int getMessageLabel(){
        return label;
    }

    public void setMessageText(String messageText){
        this.messageText = messageText;
    }

    public String getMessageText(){
        return messageText;
    }
}
