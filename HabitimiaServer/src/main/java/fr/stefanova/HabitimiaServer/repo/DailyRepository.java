package fr.stefanova.HabitimiaServer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.stefanova.HabitimiaServer.entities.Daily;

public interface DailyRepository extends JpaRepository<Daily, Long> {

	List<Daily> findAllByUserId(Long userId);

}

