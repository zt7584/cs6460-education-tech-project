package edu.dev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;

import edu.dev.entity.MongoCustomer;

@SpringBootApplication
@EnableAutoConfiguration
public class EduTechApplication {
	private static final Logger log = LoggerFactory.getLogger(EduTechApplication.class);

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(EduTechApplication.class, args);

		MongoOperations mongoTemplate = (MongoOperations) context.getBean("mongoTemplate");
		mongoTemplate.dropCollection(MongoCustomer.class);
		mongoTemplate.insert(new MongoCustomer("mickey", "mouse"));

	}
}
