package fr.stefanova.HabitimiaServer.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.stefanova.HabitimiaServer.entities.AdventurerClass;
import fr.stefanova.HabitimiaServer.entities.Avatar;
import fr.stefanova.HabitimiaServer.entities.Daily;
import fr.stefanova.HabitimiaServer.entities.Day;
import fr.stefanova.HabitimiaServer.entities.Quest;
import fr.stefanova.HabitimiaServer.entities.Repetition;
import fr.stefanova.HabitimiaServer.entities.Statistics;
import fr.stefanova.HabitimiaServer.entities.User;
import fr.stefanova.HabitimiaServer.repo.DailyRepository;
import fr.stefanova.HabitimiaServer.repo.QuestRepository;
import fr.stefanova.HabitimiaServer.repo.RepetitionRepository;
import fr.stefanova.HabitimiaServer.repo.StatisticsRepository;
import fr.stefanova.HabitimiaServer.repo.UserRepository;

@RestController
public class BasicRestController {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	DailyRepository dailyRepository;
	@Autowired
	StatisticsRepository statisticsRepository;	
	@Autowired
	RepetitionRepository repetitionRepository;
	@Autowired
	QuestRepository questRepository;
	
	public BasicRestController() {
		System.err.println("hello");
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<Object> login(String username, String password) {
		return new ResponseEntity<Object>( userRepository.findByUsernameAndPassword(username, password) ,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<Object> register(String username, String email, String password, String avatar) {
		
		Statistics stats = new Statistics();
		statisticsRepository.save(stats);
		User user = new User(username, email, password, Avatar.valueOf(avatar), stats);
		userRepository.save(user);
		//user with stats
		User user_created = userRepository.findTop1ByOrderByIdDesc();
		return new ResponseEntity<Object>(user_created ,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update-user", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<Object> updateUserStatistics(Long userId, 
												Long HP) {
		User user = userRepository.findById(userId).get();
		if (HP != null) {
			user.getStatistics().setHP(HP);
		}
		statisticsRepository.save(user.getStatistics());
		
		return new ResponseEntity<Object>(userRepository.findById(userId) ,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all-users", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<Object> all() {
		List<User> users = userRepository.findAll();
		return new ResponseEntity<Object>(users ,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all-dailies", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<List<Daily> > allDailies(Long userId) {
		List<Daily> users = dailyRepository.findAllByUserId(userId);
		return new ResponseEntity<List<Daily> >(users ,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/create-daily", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<Object> createDailies(Long userId, 
												String name,
												String details,
												AdventurerClass difficulty,
												@RequestParam(value="days")List<Day> days
												) {
		User user = userRepository.findById(userId).get();
		Daily daily = new Daily(user, name, details, difficulty);
		daily = dailyRepository.save(daily);
		for (Day day:days) {
			Repetition repetition = new Repetition(user, daily, day);
			repetitionRepository.save(repetition);			
		}
		daily = dailyRepository.findById(daily.getId()).get();
		return new ResponseEntity<Object>(daily ,HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/repetitions-for-daily", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<Object> repetitionsForDaily(Long dailyId) {
		List<Repetition> repetitions = repetitionRepository.findByDailyId(dailyId);			
		
		return new ResponseEntity<Object>(repetitions ,HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "/remove-daily", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<Object> removeDaily(Long dailyId) {
		repetitionRepository.deleteAllByDailyId(dailyId);
		dailyRepository.deleteById(dailyId);
		return new ResponseEntity<Object>(null ,HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/all-quests", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<List<Quest> > allQuests(Long userId) {
		List<Quest> quests = questRepository.findAllByUserId(userId);
		return new ResponseEntity<List<Quest> >(quests ,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/create-quest", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<Object> createQuest(Long userId, 
												String name,
												String details,
												AdventurerClass difficulty
												) {
		User user = userRepository.findById(userId).get();
		Quest quest = new Quest(user, name, details, difficulty);
		quest = questRepository.save(quest);
		return new ResponseEntity<Object>(quest ,HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "/edit-quest", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<Object> editQuest(Long questId,
											String name,
											String details,
											AdventurerClass difficulty) {
		
		Quest quest = questRepository.findById(questId).get();
		if (name != null) {
			quest.setName(name);
		}		
		if (details != null) {
			quest.setDetails(details);
		}
		if (difficulty != null) {
			quest.setDifficulty(difficulty);
		}
		quest = questRepository.save(quest);
		return new ResponseEntity<Object>(quest ,HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "/remove-quest", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<Object> removeQuest(Long questId) {
		questRepository.deleteById(questId);
		return new ResponseEntity<Object>(null ,HttpStatus.OK);
	}
	


}
