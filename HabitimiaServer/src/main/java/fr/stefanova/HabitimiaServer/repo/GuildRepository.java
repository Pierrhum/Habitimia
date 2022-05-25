package fr.stefanova.HabitimiaServer.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.stefanova.HabitimiaServer.entities.Guild;

public interface GuildRepository extends JpaRepository<Guild, Long> {

}

