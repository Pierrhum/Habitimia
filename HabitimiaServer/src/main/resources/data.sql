-- first user
INSERT INTO statistics (adventurer_class, HP, battles_won, all_battles) VALUES ('A', 10, 0, 0);
INSERT INTO user (username, email, password, avatar, statistics_id) VALUES ('Mia', 'mia@love.pierre', '12345', 'MAGICIAN', 1);

-- second user
INSERT INTO statistics (adventurer_class, HP, battles_won, all_battles) VALUES ('A', 10, 0, 0);
INSERT INTO user (username, email, password, avatar, statistics_id) VALUES ('Pierre', 'pierre@love.mia', '12345',  'WARRIOR', 2);

-- guild
INSERT INTO guild (name) VALUES ('Dead End');