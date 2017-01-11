package com.example.ofir.bopofinal.Messages;

/**
 * Created by Alonka on 09-Jan-17.
 */

public class MessageDetails {
    private String message_id, user_id, sender_id, event_id,category_name, title, description, status;

    public MessageDetails
            (String message_id, String user_id, String sender_id, String event_id, String category_name, String title, String description, String status) {

        this.message_id = message_id;
        this.user_id = user_id;
        this.sender_id = sender_id;
        this.event_id = event_id;
        this.category_name = category_name;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public String getMessageID() {
        return message_id;
    }

    public void setMessageID(String message_id) {
        this.message_id = message_id;
    }

    public String getUserID() {
        return user_id;
    }

    public void setUserID(String user_id) {
        this.user_id = user_id;
    }

    public String getSenderID() {
        return sender_id;
    }

    public void setSenderID(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getEventId() {
        return event_id;
    }

    public void setEventId(String event_id) {
        this.event_id = event_id;
    }

    public String getCategoryName() {
        return category_name;
    }

    public void setCategoryName(String category_name) {
        this.category_name = category_name;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

