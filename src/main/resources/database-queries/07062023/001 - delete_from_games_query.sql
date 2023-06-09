#Products
DELETE FROM games;

ALTER SEQUENCE games_id_seq RESTART WITH 1;

DELETE FROM consoles;

ALTER SEQUENCE consoles_id_seq RESTART WITH 1;

#Photos
DELETE FROM game_photos;

ALTER SEQUENCE game_photos_id_seq RESTART WITH 1;

DELETE FROM console_photos;

ALTER SEQUENCE console_photos_id_seq RESTART WITH 1;