package fr.stefanova.HabitimiaServer.entities;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Quest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Access(AccessType.PROPERTY)
	private Long id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Guild guild;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "details")
	private String details;
	
	@Column(name = "difficulty")
	@Enumerated(EnumType.STRING)
	private AdventurerClass difficulty;
	
	@Column(name = "owner_type")
	@Enumerated(EnumType.STRING)
	private OwnerType ownerType;
	

	public Quest() {
		super();
	}
	public Quest(User user, String name, String details, AdventurerClass difficulty) {
		super();
		this.user = user;
		this.name = name;
		this.details = details;
		this.difficulty = difficulty;
		this.ownerType = OwnerType.User;
	}
	
	public Quest(Guild guild, String name, String details, AdventurerClass difficulty) {
		super();
		this.guild = guild;
		this.name = name;
		this.details = details;
		this.difficulty = difficulty;
		this.ownerType = OwnerType.Guild;
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
	public Guild getGuild() {
		return guild;
	}
	public void setGuild(Guild guild) {
		this.guild = guild;
	}

}
