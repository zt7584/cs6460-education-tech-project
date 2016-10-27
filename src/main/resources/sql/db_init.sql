/*
 * Create the DB only once
 */
CREATE DATABASE IF NOT EXISTS edu_cs6460;
USE edu_cs6460;

/*
 * Drop all the old tables
 */
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS proposal;
DROP TABLE IF EXISTS user_proposal_relationship;

/*
 * Create tables using the latest schema
 */
CREATE TABLE IF NOT EXISTS user (
  id INT AUTO_INCREMENT PRIMARY KEY,
  email VARCHAR(128) NOT NULL,
  name VARCHAR(128) NOT NULL,
  password VARCHAR(32) NOT NULL,
  role INT NOT NULL
);

/* 0: instructor role, 1: student role*/
INSERT INTO user (email, name, password, role) VALUES ('instructor@gatech.edu', 'Instructor Inspiration', 'password', 0);

CREATE TABLE IF NOT EXISTS proposal (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(256) NOT NULL,
  description VARCHAR(1024) NOT NULL,
  status INT NOT NULL,
  created_at DATETIME NOT NULL,
  last_updated_at DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS user_proposal_relationship (
  uid INT,
  pid INT,
  relationship INT,
  PRIMARY KEY (uid, pid, relationship),
  FOREIGN KEY (uid) REFERENCES user(id),
  FOREIGN KEY (pid) REFERENCES proposal(id)
);