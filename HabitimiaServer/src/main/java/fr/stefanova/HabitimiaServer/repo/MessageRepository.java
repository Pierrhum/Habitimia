package fr.stefanova.HabitimiaServer.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.stefanova.HabitimiaServer.entities.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

	List<Message> findByGuildAndDateAfter(Long guildId, Date sevenDaysAgo);
	
	List<Message> findByGuildIdAndDateAfter(Long guildId, Date sevenDaysAgo);


	List<Message> findByGuildId(Long guildId);

//	List<Message> findAllWhereDateGreaterThan(Date sevenDaysAgo);

//	List<Message> findAllByGuildWhereDateGreaterThan(Long guildId, Date sevenDaysAgo);
//
//	

}

