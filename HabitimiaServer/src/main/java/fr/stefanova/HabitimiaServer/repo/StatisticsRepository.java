package fr.stefanova.HabitimiaServer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.stefanova.HabitimiaServer.entities.Statistics;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
	
	Statistics findTop1ByOrderByIdDesc();


	  
}

