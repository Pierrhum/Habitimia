package fr.stefanova.HabitimiaServer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.stefanova.HabitimiaServer.entities.Quest;

public interface QuestRepository extends JpaRepository<Quest, Long> {

	List<Quest> findAllByUserId(Long userId);

	List<Quest> findAllByGuildId(Long guildId);
	  
}

