package fr.stefanova.HabitimiaServer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.stefanova.HabitimiaServer.entities.User;

public interface GuildRepository extends JpaRepository<User, Long> {
	  
}

