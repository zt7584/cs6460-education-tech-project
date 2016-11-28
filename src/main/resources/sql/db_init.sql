/*
 * Create the DB only once
 */
CREATE DATABASE IF NOT EXISTS edu_cs6460;
USE edu_cs6460;

/*
 * Drop all the old tables
 */
DROP TABLE IF EXISTS api_usage_statistic_result;
DROP TABLE IF EXISTS api_usage_entry;
DROP TABLE IF EXISTS user_progress_update;
DROP TABLE IF EXISTS user_proposal_relationship;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS proposal_modification_history;
DROP TABLE IF EXISTS proposal;
DROP TABLE IF EXISTS grading_rubric;

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
INSERT INTO user (email, name, password, role) VALUES ('tzhao39@gatech.edu', 'Teng Zhao', '1234', 1);

/* 0: pending, 1: approved */
CREATE TABLE IF NOT EXISTS proposal (
  id INT AUTO_INCREMENT PRIMARY KEY,
  title VARCHAR(256) NOT NULL,
  description VARCHAR(1024) NOT NULL,
  status INT NOT NULL,
  created_at DATETIME NOT NULL,
  last_updated_at DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS proposal_modification_history (
  id INT AUTO_INCREMENT PRIMARY KEY,
  pid INT NOT NULL,
  subject VARCHAR(256) NOT NULL,
  modification VARCHAR(1024) NOT NULL,
  last_updated_at DATETIME NOT NULL,
  FOREIGN KEY (pid) REFERENCES proposal(id)
);

CREATE TABLE IF NOT EXISTS user_proposal_relationship (
  uid INT NOT NULL,
  pid INT NOT NULL,
  relationship INT NOT NULL,
  status INT NOT NULL,
  PRIMARY KEY (uid, pid, relationship),
  FOREIGN KEY (uid) REFERENCES user(id),
  FOREIGN KEY (pid) REFERENCES proposal(id)
);

/* user can only submit progress update, after proposal is approved */
CREATE TABLE IF NOT EXISTS user_progress_update (
  id INT AUTO_INCREMENT PRIMARY KEY,
  pid INT NOT NULL,
  uid INT NOT NULL,
  progress VARCHAR(1024) NOT NULL,
  created_at DATETIME NOT NULL,
  FOREIGN KEY (uid) REFERENCES user(id),
  FOREIGN KEY (pid) REFERENCES proposal(id)
);

CREATE TABLE IF NOT EXISTS grading_rubric (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(256) NOT NULL,
  statistic_entry VARCHAR(256) NOT NULL,
  threshold INT NOT NULL,
  operator VARCHAR(2) NOT NULL
);

CREATE TABLE IF NOT EXISTS api_usage_entry (
  id INT AUTO_INCREMENT PRIMARY KEY,
  uid INT NOT NULL,
  db INT NOT NULL,
  query VARCHAR(1024) NOT NULL,
  requested_at DATETIME NOT NULL,
  FOREIGN KEY (uid) REFERENCES user(id)
);

CREATE TABLE IF NOT EXISTS api_usage_statistic_result (
  id INT AUTO_INCREMENT PRIMARY KEY,
  aue_id INT NOT NULL,
  statistic_entry VARCHAR(256) NOT NULL,
  result INT NOT NULL,
  FOREIGN KEY (aue_id) REFERENCES api_usage_entry(id)
);