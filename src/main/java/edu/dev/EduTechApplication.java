package edu.dev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class EduTechApplication {
	private static final Logger log = LoggerFactory.getLogger(EduTechApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EduTechApplication.class, args);
	}
}
