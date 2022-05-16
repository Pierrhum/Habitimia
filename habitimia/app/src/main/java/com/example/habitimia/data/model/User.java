package com.example.habitimia.data.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class User implements Serializable {
    private Long id;
    private String username;

    private String email;

    private String password;

    private Avatar avatar;

    private Statistics statistics;

    private Guild guild;

    // Debug constructor
    public User() {
        username = "Username";
        email = "Email@blabla.oui";
        password = "motdepasse";
        avatar = Avatar.MAGICIAN;
        statistics = new Statistics();
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(JSONObject statsJSON) {
        try {
            this.id = statsJSON.getLong("id");
            this.username = statsJSON.getString("username");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }




    public User(String username, String email, String password, Avatar avatar, Statistics statistics) {
        super();
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
        this.statistics = statistics;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public Guild getGuild() {
        return guild;
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

}
