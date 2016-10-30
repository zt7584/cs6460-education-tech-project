package edu.dev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import edu.dev.entity.MongoCustomer;
import org.json.simple.JSONObject;

@Service
public class MongoService {

	@Autowired
	MongoOperations mongoTemplate;

	public List<?> executeQuery(String query) {
		String addedBrackets = "{" + query + "}";
		System.out.println("Input query with added brackets " + addedBrackets);
		BasicQuery basicQuery = new BasicQuery(addedBrackets);
		System.out.println("Basic Query as MongoDB input: " + basicQuery.toString());
		return mongoTemplate.find(basicQuery, MongoCustomer.class);
	}
}
