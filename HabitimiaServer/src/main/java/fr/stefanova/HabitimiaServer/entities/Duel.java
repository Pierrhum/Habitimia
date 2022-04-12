package fr.stefanova.HabitimiaServer.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Duel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private User challenger;
	
	@ManyToOne
	private User challenged;
	
	@Column(name = "won_by_challenger")
	private boolean wonByChallenger;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getChallenger() {
		return challenger;
	}

	public void setChallenger(User challenger) {
		this.challenger = challenger;
	}

	public User getChallenged() {
		return challenged;
	}

	public void setChallenged(User challenged) {
		this.challenged = challenged;
	}

	public boolean isWonByChallenger() {
		return wonByChallenger;
	}

	public void setWonByChallenger(boolean wonByChallenger) {
		this.wonByChallenger = wonByChallenger;
	}
	
}
