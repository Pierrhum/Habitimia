package fr.stefanova.HabitimiaServer.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.stefanova.HabitimiaServer.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsernameAndPassword(String username, String password);

	User findTop1ByOrderByIdDesc();

	  
}

