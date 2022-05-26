package fr.stefanova.HabitimiaServer.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
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
import fr.stefanova.HabitimiaServer.entities.Guild;
import fr.stefanova.HabitimiaServer.entities.Message;
import fr.stefanova.HabitimiaServer.entities.OwnerType;
import fr.stefanova.HabitimiaServer.entities.Quest;
import fr.stefanova.HabitimiaServer.entities.Repetition;
import fr.stefanova.HabitimiaServer.entities.Statistics;
import fr.stefanova.HabitimiaServer.entities.User;
import fr.stefanova.HabitimiaServer.repo.DailyRepository;
import fr.stefanova.HabitimiaServer.repo.GuildRepository;
import fr.stefanova.HabitimiaServer.repo.QuestRepository;
import fr.stefanova.HabitimiaServer.repo.RepetitionRepository;
import fr.stefanova.HabitimiaServer.repo.StatisticsRepository;
import fr.stefanova.HabitimiaServer.repo.UserRepository;
import fr.stefanova.HabitimiaServer.repo.MessageRepository;

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
	@Autowired
	GuildRepository guildRepository;	
	@Autowired
	MessageRepository messageRepository;
	
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
			user.getStatistics().setHP(HP + user.getStatistics().getHP());
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
		List<Daily> dailies = dailyRepository.findAllByUserId(userId);
		for (Daily daily: dailies) {
			List<Repetition> repetitions = repetitionRepository.findByDailyId(daily.getId());
			for (Repetition repetition: repetitions) {
				repetition.setDaily(null);
			}
			daily.setRepetitions(repetitions);
		}
		return new ResponseEntity<List<Daily> >(dailies ,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/all-dailies-for-day", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<List<Daily> > allDailiesForDay(Long userId, Day day) {
		List<Daily> dailies = dailyRepository.findAllByUserId(userId);
		List<Daily> dailies_for_day = new ArrayList<>();
		for (Daily daily: dailies) {
			boolean hasDay = false;
			List<Repetition> repetitions = repetitionRepository.findAllByDailyId(daily.getId());
			for (Repetition repetition: repetitions) {
				repetition.setDaily(null);
				if (repetition.getDay() == day) {
					hasDay= true;
					daily.setRepetitions(repetitions);
				}
			}
			if (hasDay)
				dailies_for_day.add(daily);

		}
//		for (Daily daily: dailies) {
//			boolean hasDay = false;
//			List<Repetition> repetitions = repetitionRepository.findByDailyId(daily.getId());
//			for (Repetition repetition: repetitions) {
//				if (repetition.getDay() == day)
//					hasDay= true;
//			}
//			if (hasDay)
//				daily.setRepetitions(repetitions);
//			else {}
////				dailies.remove(daily);
//		}
		
		return new ResponseEntity<List<Daily> >(dailies_for_day ,HttpStatus.OK);
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
		List<Repetition> savedRepetitions = new ArrayList<>();
		daily = dailyRepository.save(daily);
		for (Day day:days) {
			Repetition repetition = new Repetition(daily, day);
			Repetition saved = repetitionRepository.save(repetition);	
//			repetition.setDaily(null);
			savedRepetitions.add(new Repetition(saved.getId(), null, day));
		}
		daily.setRepetitions(savedRepetitions);
		
		List<Repetition> repetitions = repetitionRepository.findAll();
		return new ResponseEntity<Object>(daily ,HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "/update-daily", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<Object> updateDailies(Long dailyId, 
												String name,
												String details,
												AdventurerClass difficulty,
												@RequestParam(value="days")List<Day> days
												) {

		Daily daily = dailyRepository.findById(dailyId).get();
		if (name != null) {
			daily.setName(name);
		}		
		if (details != null) {
			daily.setDetails(details);
		}
		if (difficulty != null) {
			daily.setDifficulty(difficulty);
		}
		daily = dailyRepository.save(daily);
		if (days.size() != 0) {
			
			repetitionRepository.deleteAllByDailyId(dailyId);
			daily.setRepetitions(new ArrayList<>());
			for (Day day:days) {
				Repetition repetition = new Repetition(daily, day);
				Repetition saved = repetitionRepository.save(repetition);	
//				repetition.setDaily(null);
//				daily.getRepetitions().add(new Repetition(saved.getId(), null, day));
			}
		}else {
			
//			List<Repetition> repetitions = repetitionRepository.findByDailyId(daily.getId());
//			for (Repetition repetition: repetitions) {
//				repetition.setDaily(null);
//			}
//			daily.setRepetitions(repetitions);
		}

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
	public ResponseEntity<List<Quest> > allQuests(Long ownerId,OwnerType ownerType) {
		List<Quest> quests = null;
		if (ownerType == OwnerType.User) {
			quests =questRepository.findAllByUserId(ownerId);
		}else if (ownerType == OwnerType.Guild) {
			quests = questRepository.findAllByGuildId(ownerId);
		}

		return new ResponseEntity<List<Quest> >(quests ,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/create-quest", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<Object> createQuest(Long ownerId,
												OwnerType ownerType,
												String name,
												String details,
												AdventurerClass difficulty
												) {
		Quest quest = null;
		if (ownerType == OwnerType.User) {
			User user = userRepository.findById(ownerId).get();
			quest = new Quest(user, name, details, difficulty);
		}else if (ownerType == OwnerType.Guild) {
			Guild guild = guildRepository.findById(ownerId).get();
			quest = new Quest(guild, name, details, difficulty);
		}

		quest = questRepository.save(quest);
		return new ResponseEntity<Object>(quest ,HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "/update-quest", method = RequestMethod.GET, produces = {"application/json"})
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
	
	@Transactional
	@RequestMapping(value = "/all-guild-members", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<Object> allGuildQuest(Long guildId) {
		List<User> users = userRepository.findAllUsersFromGuild(guildId);
		for (User user:users) {
//			Statistics stats = statisticsRepository.findUserId(user.getId());
//			user.setStatistics(stats);
		}
		return new ResponseEntity<Object>(users ,HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "/all-messages-for-past-week", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<List<Message> > allMessagesForPastWeek(Long guildId) {
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DAY_OF_MONTH, -7);
		Date sevenDaysAgo = cal.getTime();
		
		List<Message> messages = messageRepository.findByGuildIdAndDateAfter(guildId, sevenDaysAgo);

		return new ResponseEntity<List<Message> >(messages ,HttpStatus.OK);
	}
	
	@Transactional
	@RequestMapping(value = "/all-messages-for-today", method = RequestMethod.GET, produces = {"application/json"})
	public ResponseEntity<List<Message> > allMessagesForToday(Long guildId) {
		Calendar cal = new GregorianCalendar();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date sevenDaysAgo = cal.getTime();
		
		List<Message> messages = messageRepository.findByGuildIdAndDateAfter(guildId, sevenDaysAgo);

		return new ResponseEntity<List<Message> >(messages ,HttpStatus.OK);
	}
	
//	@Transactional
//	@RequestMapping(value = "/last-message", method = RequestMethod.GET, produces = {"application/json"})
//	public ResponseEntity<Message> LastMessage(Long guildId) {
//
//		
//		Message message = messageRepository.findByGuildIdTop(guildId);
//
//		return new ResponseEntity<Message>(message ,HttpStatus.OK);
//	}


}
