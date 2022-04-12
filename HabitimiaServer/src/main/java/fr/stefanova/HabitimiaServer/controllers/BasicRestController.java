package fr.stefanova.HabitimiaServer.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.stefanova.HabitimiaServer.entities.Avatar;
import fr.stefanova.HabitimiaServer.entities.Statistics;
import fr.stefanova.HabitimiaServer.entities.User;
import fr.stefanova.HabitimiaServer.repo.StatisticsRepository;
import fr.stefanova.HabitimiaServer.repo.UserRepository;

@RestController
public class BasicRestController {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	StatisticsRepository statisticsRepository;
	
	public BasicRestController() {
		System.err.println("hello");
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<Object> login(String username, String password) {
//		boolean user_identified = userRepository.findByUsernameAndPassword(username, password) != null; 
////		User user = userRepository.findByUsernameAndPassword("Mia", "12345") ; 
		return new ResponseEntity<Object>( userRepository.findByUsernameAndPassword(username, password) ,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<Object> login(String username, String email, String password, String avatar) {
		
		Statistics stats = new Statistics();
		statisticsRepository.save(stats);
		User user = new User(username, email, password, Avatar.valueOf(avatar), stats);
		userRepository.save(user);
		//user with stats
		User user_created = userRepository.findTop1ByOrderByIdDesc();
		return new ResponseEntity<Object>(user_created ,HttpStatus.OK);
	}
	
	
	
	
	


}
