-- first user
INSERT INTO statistics (adventurer_class, HP, battles_won, all_battles, experience) VALUES ('A', 10, 3, 4, 3);
INSERT INTO user (username, email, password, avatar, statistics_id) VALUES ('Mia', 'mia@love.pierre', '123456', 'MAGICIAN', 1);

-- second user
INSERT INTO statistics (adventurer_class, HP, battles_won, all_battles, experience) VALUES ('A', 10, 3, 2, 3);
INSERT INTO user (username, email, password, avatar, statistics_id) VALUES ('Pierre', 'pierre@love.mia', '123456',  'WARRIOR', 2);

-- guild
INSERT INTO guild (name) VALUES ('Dead End');
UPDATE user SET guild_id = 1 WHERE id = 1;

UPDATE user SET guild_id = 1 WHERE id = 2;

-- daily for user 1
INSERT INTO daily (name, user_id, details, difficulty) VALUES ('Emails', 1, 'Check the 3 mail-boxes', 'D');
INSERT INTO repetition ( daily_id, day) values (1, 'MONDAY');
INSERT INTO repetition ( daily_id, day) values (1, 'TUESDAY');
INSERT INTO repetition ( daily_id, day) values (1, 'WEDNESDAY');
INSERT INTO repetition ( daily_id, day) values (1, 'THURSDAY');
INSERT INTO repetition ( daily_id, day) values (1, 'FRIDAY');
INSERT INTO daily (name, user_id, details, difficulty) VALUES ('Walk Dogzilla', 1, '', 'D');
INSERT INTO repetition ( daily_id, day) values (2, 'THURSDAY');
INSERT INTO repetition ( daily_id, day) values (2, 'WEDNESDAY');
INSERT INTO repetition ( daily_id, day) values (2, 'FRIDAY');
INSERT INTO daily (name, user_id, details, difficulty) VALUES ('Eat 2 fruits', 1, 'An apple a day keeps the germs away', 'D');
INSERT INTO repetition ( daily_id, day) values (3, 'MONDAY');
INSERT INTO repetition ( daily_id, day) values (3, 'TUESDAY');
INSERT INTO repetition ( daily_id, day) values (3, 'WEDNESDAY');
INSERT INTO repetition ( daily_id, day) values (3, 'THURSDAY');
INSERT INTO repetition ( daily_id, day) values (3, 'FRIDAY');
INSERT INTO repetition ( daily_id, day) values (3, 'SATURDAY');
INSERT INTO repetition ( daily_id, day) values (3, 'SUNDAY');
INSERT INTO daily (name, user_id, details, difficulty) VALUES ('Drink a liter of water', 1, 'Good luck friend', 'D');
INSERT INTO repetition ( daily_id, day) values (4, 'MONDAY');
INSERT INTO repetition ( daily_id, day) values (4, 'TUESDAY');
INSERT INTO repetition ( daily_id, day) values (4, 'WEDNESDAY');
INSERT INTO repetition ( daily_id, day) values (4, 'THURSDAY');
INSERT INTO repetition ( daily_id, day) values (4, 'FRIDAY');
INSERT INTO repetition ( daily_id, day) values (4, 'SATURDAY');
INSERT INTO repetition ( daily_id, day) values (4, 'SUNDAY');

-- quests for user 1
INSERT INTO quest (name, user_id, details, difficulty) VALUES ('buy Dogzilla food', 1, 'Buy new food.', 'C');
INSERT INTO quest (name, user_id, details, difficulty) VALUES ('Dogzilla has a b-day', 1, 'Buy some presents.', 'B');
INSERT INTO quest (name, user_id, details, difficulty) VALUES ('Send algebra homework', 1, 'This will take at least 2 days.', 'S');
INSERT INTO quest (name, user_id, details, difficulty) VALUES ('Get snacks for the movie night', 1, 'Nom nom', 'F');
INSERT INTO quest (name, user_id, details, difficulty) VALUES ('Rent a movie', 1, 'Go to Barbara.', 'S');
INSERT INTO quest (name, user_id, details, difficulty) VALUES ('Microwave broke', 1, 'Call the landlord.', 'S');

-- quests for guild 1
INSERT INTO quest (name, guild_id, details, difficulty) VALUES ('Write the report', 1, 'Buy food.', 'C');
INSERT INTO quest (name, guild_id, details, difficulty) VALUES ('Go to the beach', 1, 'Buy some presents.', 'B');


-- messages in guild chat
INSERT INTO message (guild_id, user_id, text, date) VALUES (1, 1, 'My glasses :(', '2022-05-22');
INSERT INTO message (guild_id, user_id, text, date) VALUES (1, 1, 'Can you get them for me?', '2022-05-24');
INSERT INTO message (guild_id, user_id, text, date) VALUES (1, 2, 'Alright, from where', '2022-05-24');
INSERT INTO message (guild_id, user_id, text, date) VALUES (1, 1, 'Maastricht', '2022-05-26');


-- more users
INSERT INTO statistics (adventurer_class, HP, battles_won, all_battles, experience) VALUES ('A', 10, 5, 6,5 );
INSERT INTO user (username, email, password, avatar, statistics_id) VALUES ('Gogo', 'mia@love.pierre', '123456', 'MAGICIAN', 3);


INSERT INTO statistics (adventurer_class, HP, battles_won, all_battles, experience) VALUES ('A', 10, 3, 2, 4);
INSERT INTO user (username, email, password, avatar, statistics_id) VALUES ('Bennito', 'pierre@love.mia', '123456',  'WARRIOR', 4);

INSERT INTO statistics (adventurer_class, HP, battles_won, all_battles, experience) VALUES ('A', 20, 3, 2, 6);
INSERT INTO user (username, email, password, avatar, statistics_id) VALUES ('GindaMAN', 'pierre@love.mia', '123456',  'WARRIOR', 5);

INSERT INTO statistics (adventurer_class, HP, battles_won, all_battles, experience) VALUES ('A', 20, 3, 2, 6);
INSERT INTO user (username, email, password, avatar, statistics_id) VALUES ('Mariu', 'pierre@love.mia', '123456',  'WARRIOR', 6);
