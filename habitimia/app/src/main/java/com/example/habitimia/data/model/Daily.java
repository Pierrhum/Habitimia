package com.example.habitimia.data.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Daily implements Serializable {

    private Long id;

    private User user;

    private String name;

    private String details;

    private AdventurerClass difficulty;

    private List<Repetition> repetitions = new ArrayList<>();

    public Daily(){
        this.repetitions = new ArrayList<>();
    }
    public Daily(User user, String name, String details, AdventurerClass difficulty) {
        this.user = user;
        this.name = name;
        this.details = details;
        this.difficulty = difficulty;
        this.repetitions = new ArrayList<>();
    }

    public Daily(JSONObject quest) {
        try {
            this.id = quest.getLong("id");
            this.name = quest.getString("name");
            this.details = quest.getString("details");
            this.difficulty = AdventurerClass.valueOf(quest.getString("difficulty"));
            this.repetitions = new ArrayList<>();
            JSONArray repetitions_JSONArray =  new JSONArray(quest.getString("repetitions"));
            for(int i = 0; i < repetitions_JSONArray.length(); i++){
                JSONObject repetition = repetitions_JSONArray.getJSONObject(i);
                if (repetition.getString("day") != null)
                this.repetitions.add(new Repetition(repetition));
            }
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public AdventurerClass getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(AdventurerClass difficulty) {
        this.difficulty = difficulty;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public List<Repetition> getRepetitions() {
        return repetitions;
    }
    public void setRepetitions(List<Repetition> repetitions) {
        this.repetitions = repetitions;
    }


}
