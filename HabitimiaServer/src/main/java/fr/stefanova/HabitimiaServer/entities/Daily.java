package fr.stefanova.HabitimiaServer.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Daily {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Access(AccessType.PROPERTY)
	private Long id;
	
	@ManyToOne
	private User user;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "details")
	private String details;
	
	@Column(name = "difficulty")
	@Enumerated(EnumType.STRING)
	private AdventurerClass difficulty;
	
	@OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
	private List<Repetition> repetitions;

	public Daily() {
		super();
	}
	public Daily(User user, String name, String details, AdventurerClass difficulty) {
		super();
		this.user = user;
		this.name = name;
		this.details = details;
		this.difficulty = difficulty;
		this.repetitions= new ArrayList<>() ;
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
