package fr.stefanova.HabitimiaServer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.stefanova.HabitimiaServer.entities.Daily;
import fr.stefanova.HabitimiaServer.entities.Repetition;

public interface RepetitionRepository extends JpaRepository<Repetition, Long> {

//	List<Repetition> findAllByUserId(Long userId);

	List<Repetition> findByDailyId(Long dailyId);

	void deleteAllByDailyId(Long dailyId);

	List<Repetition> findByDaily(Daily daily);

	List<Repetition> findAllByDailyId(Long id);

}

