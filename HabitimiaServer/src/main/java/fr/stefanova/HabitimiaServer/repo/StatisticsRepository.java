package fr.stefanova.HabitimiaServer.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.stefanova.HabitimiaServer.entities.Daily;
import fr.stefanova.HabitimiaServer.entities.Statistics;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
	
	Statistics findTop1ByOrderByIdDesc();




	  
}

