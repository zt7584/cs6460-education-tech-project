package edu.dev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.dev.service.MongoService;

@RestController
public class MongoRestController {

	@Autowired
	MongoService mongoService;
	
	//postman request: Body,raw,JSON, input string: 'first':'mickey' OR "first":"mickey"

	@RequestMapping(value = "/mongo", method = RequestMethod.POST, produces = "application/json")
	public Object execute(@RequestBody String query) {
		System.out.println("Rest request body: " + query);
		Object obj = mongoService.executeQuery(query);
		return obj;
	}
}
