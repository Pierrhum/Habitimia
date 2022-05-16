package com.example.habitimia.data.model;

import java.util.List;

public class Daily {

    private Long id;

    private User user;

    private String name;

    private String details;

    private AdventurerClass difficulty;

    private List<Repetition> repetitions;

    public Daily() {
    }
    public Daily(User user, String name, String details, AdventurerClass difficulty) {
        this.user = user;
        this.name = name;
        this.details = details;
        this.difficulty = difficulty;
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
