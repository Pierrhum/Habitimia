package fr.stefanova.HabitimiaServer.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.stefanova.HabitimiaServer.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsernameAndPassword(String username, String password);

	User findTop1ByOrderByIdDesc();
	
	@Query("select u FROM User u WHERE u.guild.id = ?1")
	public List<User> findAllUsersFromGuild(Long guildId);

	  
}

