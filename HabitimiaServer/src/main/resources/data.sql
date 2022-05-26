-- first user
INSERT INTO statistics (adventurer_class, HP, battles_won, all_battles, experience) VALUES ('A', 10, 0, 0, 0);
INSERT INTO user (username, email, password, avatar, statistics_id) VALUES ('Mia', 'mia@love.pierre', '123456', 'MAGICIAN', 1);

-- second user
INSERT INTO statistics (adventurer_class, HP, battles_won, all_battles, experience) VALUES ('A', 10, 0, 0, 0);
INSERT INTO user (username, email, password, avatar, statistics_id) VALUES ('Pierre', 'pierre@love.mia', '123456',  'WARRIOR', 2);

-- guild
INSERT INTO guild (name) VALUES ('Dead End');
UPDATE user SET guild_id = 1 WHERE id = 1;

UPDATE user SET guild_id = 1 WHERE id = 2;

-- daily for user 1
INSERT INTO daily (name, user_id, details, difficulty) VALUES ('Emails', 1, 'Check all 3 mail-boxes', 'D');
INSERT INTO repetition ( daily_id, day) values (1, 'MONDAY');
INSERT INTO repetition ( daily_id, day) values (1, 'TUESDAY');
INSERT INTO repetition ( daily_id, day) values (1, 'WEDNESDAY');
INSERT INTO repetition ( daily_id, day) values (1, 'THURSDAY');
INSERT INTO repetition ( daily_id, day) values (1, 'FRIDAY');

-- quests for user 1
INSERT INTO quest (name, user_id, details, difficulty) VALUES ('Frodo', 1, 'Buy new food.', 'C');
INSERT INTO quest (name, user_id, details, difficulty) VALUES ('Frodo has a b-day', 1, 'Buy some presents.', 'B');

-- quests for guild 1
INSERT INTO quest (name, guild_id, details, difficulty) VALUES ('Write the report', 1, 'Buy food.', 'C');
INSERT INTO quest (name, guild_id, details, difficulty) VALUES ('Go to the beach', 1, 'Buy some presents.', 'B');


-- messages in guild chat
INSERT INTO message (guild_id, user_id, text, date) VALUES (1, 1, 'My glasses :(', '2022-05-22');
INSERT INTO message (guild_id, user_id, text, date) VALUES (1, 1, 'Can you get them for me?', '2022-05-24');
INSERT INTO message (guild_id, user_id, text, date) VALUES (1, 2, 'Alright, from where', '2022-05-24');
INSERT INTO message (guild_id, user_id, text, date) VALUES (1, 1, 'Maastricht', '2022-05-26');
