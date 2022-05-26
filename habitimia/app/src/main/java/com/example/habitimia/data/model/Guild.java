package com.example.habitimia.data.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Guild implements Serializable {
    private Long id;

    private String name;



    private ArrayList<User> members;

    public Guild()
    {
        this.id=1l;
        this.name="Dead End";
    }

    public Guild(ArrayList<User> members) {
        this.id=1l;
        this.name="Dead End";
        this.members = new ArrayList<User>(members);
    }

    public Guild(JSONObject guild) {
        try {
            this.id = guild.getLong("id");
            this.name = guild.getString("name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<User> getMembers() {
        return this.members;
    }

    public void setMembers(ArrayList<User> members) {
        this.members = members;
    }
}
