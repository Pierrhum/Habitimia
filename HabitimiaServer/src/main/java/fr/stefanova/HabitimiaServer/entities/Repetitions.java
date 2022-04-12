package fr.stefanova.HabitimiaServer.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Repetitions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private User user;
	
	@ManyToOne
	private Daily daily;
	
	@Column(name = "day")
	@Enumerated(EnumType.STRING)
	private Day day;
	


}
