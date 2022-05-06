package fr.stefanova.HabitimiaServer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.stefanova.HabitimiaServer.entities.Daily;

public interface QuestRepository extends JpaRepository<Daily, Long> {
	  
}

