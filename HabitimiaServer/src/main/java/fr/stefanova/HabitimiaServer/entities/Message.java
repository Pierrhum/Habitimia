package fr.stefanova.HabitimiaServer.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Guild guild;
	
	@ManyToOne
	private User user;
	
	@Column(name = "text")
	private String text;
	
	@Column(name = "date")
	private Date date;

	public Long getId() {
		return id;
	}
	
	public Message() {}
	
	public Message(Guild guild, User user, String text, Date date) {
		super();
		this.guild = guild;
		this.user = user;
		this.text = text;
		this.date = date;
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
