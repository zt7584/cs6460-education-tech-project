package edu.dev.service;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.dev.entity.OnlineJudgeResponse;
import edu.dev.entity.StatisticEntry;
import edu.dev.entity.User;
import edu.dev.util.CommonUtils;
import edu.dev.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import edu.dev.entity.MongoCustomer;

import static com.sun.tools.doclint.Entity.ne;

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

	public OnlineJudgeResponse executeQueryForOnlineJudge(String query) {
		OnlineJudgeResponse onlineJudgeResponse = new OnlineJudgeResponse();
		String addedBrackets = "{" + query + "}";
		BasicQuery basicQuery = new BasicQuery(addedBrackets);
		Date startTime = new Date();
		Object rawResponse = null;
		try {
			rawResponse = mongoTemplate.find(basicQuery, MongoCustomer.class);
		} catch (Throwable t) {
			rawResponse = t;
		}
		Date endTime = new Date();

		try {
			onlineJudgeResponse.setRawResponse(new ObjectMapper().writeValueAsString(rawResponse));
		} catch (Throwable t) {
			t.printStackTrace();
		}
		onlineJudgeResponse.addStatisticEntry(
				new StatisticEntry(StatisticEntry.NAME.EXEC_TIME, endTime.getTime() - startTime.getTime(), "ms"));

		return onlineJudgeResponse;
	}
}
