package com.example.habitimia.data.model;

public class Repetition {
    private Long id;

//	private User user;
//
    private Daily daily;

    private Day day;


    public Repetition() {
    }

    public Repetition(User user, Daily daily, Day day) {
//		this.user = user;
        this.daily = daily;
        this.day = day;
    }

    public Repetition(Daily daily, Day day) {
//		this.user = user;
        this.daily = daily;
        this.day = day;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

}
