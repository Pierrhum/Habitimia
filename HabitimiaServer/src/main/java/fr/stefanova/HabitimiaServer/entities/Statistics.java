package fr.stefanova.HabitimiaServer.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Statistics {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "adventurer_class")
	@Enumerated(EnumType.STRING)
	private AdventurerClass adventurerClass;
	
	@Column(name = "HP")
	private Long HP;
	
	@Column(name = "battles_won")
	private Long battlesWon;
	
	@Column(name = "all_battles")
	private Long allBattles;

	public Statistics() {
		super();
		this.adventurerClass = AdventurerClass.A;
		this.HP = 10L;
		this.battlesWon = 0L;
		this.allBattles = 0L;
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

