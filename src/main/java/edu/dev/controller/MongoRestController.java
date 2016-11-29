package edu.dev.controller;

import edu.dev.entity.ApiUsageEntry;
import edu.dev.entity.ApiUsageStatisticResult;
import edu.dev.entity.GradingRubric;
import edu.dev.entity.StatisticEntry;
import edu.dev.repository.GradingRubricRepository;
import edu.dev.service.ApiUsageEntryService;
import edu.dev.service.ApiUsageStatisticResultService;
import edu.dev.service.UserService;
import edu.dev.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.dev.service.MongoService;

import java.util.Date;
import java.util.List;

@RestController
public class MongoRestController {

	@Autowired
	MongoService mongoService;

	@Autowired
	UserService userService;

	@Autowired
	ApiUsageEntryService apiUsageEntryService;

	@Autowired
	GradingRubricRepository gradingRubricRepository;

	@Autowired
	ApiUsageStatisticResultService apiUsageStatisticResultService;
	
	//postman request: Body,raw,JSON, input string: 'first':'mickey' OR "first":"mickey"

	@RequestMapping(value = "/mongo/{user_id}", method = RequestMethod.POST, produces = "application/json")
	public Object execute(@PathVariable("user_id") Integer userId, @RequestBody String query) {
		Integer verifiedUserId = null;
		try {
			if (userService.exist(userId)) {
				verifiedUserId = userId;
			} else {
				return "No user found with user id [" + userId + "]";
			}
		} catch (Throwable t) {
		}
		if (verifiedUserId == null) {
			return "Error occurred while looking for user with id [" + verifiedUserId + "]";
		}

		ApiUsageEntry apiUsageEntry = new ApiUsageEntry(verifiedUserId, Constant.DB_TYPE.MONGODB, new Date(), query);
		apiUsageEntry = apiUsageEntryService.saveApiUsageEntry(apiUsageEntry);

		List<GradingRubric> gradingRubrics = gradingRubricRepository.findAllGradingRubric();

		boolean countExecTime = false;
		for (GradingRubric gradingRubric : gradingRubrics) {
			if (gradingRubric.getStatisticEntry().equalsIgnoreCase(StatisticEntry.NAME.EXEC_TIME)) {
				countExecTime = true;
			}
		}
		Date starTime = null;
		if (countExecTime) {
			starTime = new Date();
		}
		System.out.println("Rest request body: " + query);
		Object rawResult = mongoService.executeQuery(query);
		if (countExecTime) {
			ApiUsageStatisticResult apiUsageStatisticResult
					= new ApiUsageStatisticResult(apiUsageEntry.getId(), StatisticEntry.NAME.EXEC_TIME, new Date().getTime() - starTime.getTime());
			apiUsageStatisticResultService.saveApiUsageStatisticResult(apiUsageStatisticResult);
		}
		return rawResult;
	}
}
