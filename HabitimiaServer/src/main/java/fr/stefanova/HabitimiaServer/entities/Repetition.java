package fr.stefanova.HabitimiaServer.entities;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Repetition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@ManyToOne
//	private User user;
//	
	@ManyToOne
	@Access(AccessType.PROPERTY)
	private Daily daily;
	
	@Column(name = "day")
	@Enumerated(EnumType.STRING)
	private Day day;

	
	public Repetition() {
		super();
	}

	public Repetition(Daily daily, Day day) {
		super();
//		this.user = user;
		this.daily = daily;
		this.day = day;
	}

	public Repetition(Long id, Daily daily, Day day) {
		super();
		this.id = id;
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
