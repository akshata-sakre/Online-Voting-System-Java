CREATE DATABASE voting_system;
USE voting_system;

CREATE TABLE users (
    userId INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50),
    password VARCHAR(50),
    hasVoted BOOLEAN DEFAULT FALSE
);
desc users;
SELECT * FROM users;


ALTER TABLE users AUTO_INCREMENT = 1;
DELETE FROM users WHERE userId > 0;
drop table users;

CREATE TABLE candidates (
    candidateId INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50),
    voteCount INT DEFAULT 0
);
desc candidates;
Select * from candidates;
DELETE FROM candidates WHERE candidateId > 0;
ALTER TABLE candidates ADD symbol VARCHAR(50);
ALTER TABLE candidates DROP COLUMN symbol;
ALTER TABLE candidates AUTO_INCREMENT = 1;

CREATE TABLE admin (
    username VARCHAR(50) PRIMARY KEY,
    password VARCHAR(50)
);


INSERT INTO admin VALUES ('admin', '1234');