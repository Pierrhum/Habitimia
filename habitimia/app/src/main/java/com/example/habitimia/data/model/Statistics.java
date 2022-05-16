package com.example.habitimia.data.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Statistics implements Serializable {
    private Long id;

    private AdventurerClass adventurerClass;

    private Long HP;

    private Long battlesWon;

    private Long allBattles;

    public Statistics() {
        this.adventurerClass = AdventurerClass.A;
        this.HP = 10L;
        this.battlesWon = 0L;
        this.allBattles = 0L;
    }

    public Statistics(Long id, AdventurerClass adventurerClass, Long HP, Long battlesWon, Long allBattles) {
        this.id = id;
        this.adventurerClass = adventurerClass;
        this.HP = HP;
        this.battlesWon = battlesWon;
        this.allBattles = allBattles;
    }

    public Statistics(JSONObject statsJSON) {
        try {
            this.id = statsJSON.getLong("id");
            this.adventurerClass = AdventurerClass.valueOf(statsJSON.getString("adventurerClass"));
            this.HP = statsJSON.getLong("hp");
            this.battlesWon = statsJSON.getLong("battlesWon");
            this.allBattles = statsJSON.getLong("allBattles");
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        this.battlesWon = battlesWon;
//        this.allBattles = allBattles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdventurerClass getAdventurerClass() {
        return adventurerClass;
    }

    public void setAdventurerClass(AdventurerClass adventurerClass) {
        this.adventurerClass = adventurerClass;
    }

    public Long getHP() {
        return HP;
    }

    public void setHP(Long hP) {
        HP = hP;
    }

    public Long getBattlesWon() {
        return battlesWon;
    }

    public void setBattlesWon(Long battlesWon) {
        this.battlesWon = battlesWon;
    }

    public Long getAllBattles() {
        return allBattles;
    }

    public void setAllBattles(Long allBattles) {
        this.allBattles = allBattles;
    }
}
