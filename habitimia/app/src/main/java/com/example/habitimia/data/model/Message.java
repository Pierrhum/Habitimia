package com.example.habitimia.data.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.sql.Date;


public class Message implements Serializable {
     private Long id;

    private Guild guild;

    private User user;

    private String text;

    private Date date;

    public Message(JSONObject message) {
        try {
            this.id = message.getLong("id");
            this.text = message.getString("text");
            this.user = new User(message.getJSONObject("user"));

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Message(String text, User user) {
        this.text = text;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Guild getGuild() {
        return guild;
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }




}
