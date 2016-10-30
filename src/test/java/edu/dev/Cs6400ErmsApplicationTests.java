package edu.dev;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import edu.dev.service.MongoService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class Cs6400ErmsApplicationTests {

	@Autowired
	MongoService mongoService;

	@Test
	public void testMongoQuery() {

		Object obj = mongoService.executeQuery("'first':'mickey'");
		Assert.assertNotNull(obj);
	}

}
