package edu.dev.controller;

import edu.dev.entity.*;
import edu.dev.repository.ApiUsageStatisticResultRepository;
import edu.dev.repository.GradingRubricRepository;
import edu.dev.repository.UserRepository;
import edu.dev.service.*;

import edu.dev.util.CommonUtils;
import edu.dev.util.Constant;
import edu.dev.util.SQLStatement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by tengzhao on 10/26/16.
 */
@RestController
public class MysqlRestController {

	@Autowired
	MySqlService mySqlService;

	@Autowired
	UserService userService;

	@Autowired
	GradingRubricRepository gradingRubricRepository;

	@Autowired
	ApiUsageEntryService apiUsageEntryService;

	@Autowired
	ApiUsageStatisticResultService apiUsageStatisticResultService;

	@RequestMapping(value = "/mysql/{user_id}", method = RequestMethod.POST, produces = "application/json")
	public Object execute(@PathVariable("user_id") Integer userId, @RequestBody String sql) {
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
		ApiUsageEntry apiUsageEntry = new ApiUsageEntry(verifiedUserId, Constant.DB_TYPE.MYSQL, new Date(), sql);
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
		Object rawResult = mySqlService.executeQuery(sql);
		if (countExecTime) {
			ApiUsageStatisticResult apiUsageStatisticResult
					= new ApiUsageStatisticResult(apiUsageEntry.getId(), StatisticEntry.NAME.EXEC_TIME, new Date().getTime() - starTime.getTime());
			apiUsageStatisticResultService.saveApiUsageStatisticResult(apiUsageStatisticResult);
		}
		for (GradingRubric gradingRubric : gradingRubrics) {
			if (gradingRubric.getStatisticEntry().equalsIgnoreCase(StatisticEntry.NAME.NUM_OF_JOIN)) {
				apiUsageStatisticResultService.saveApiUsageStatisticResult(
						new ApiUsageStatisticResult(apiUsageEntry.getId(), StatisticEntry.NAME.NUM_OF_JOIN, CommonUtils.countOfString(sql, Constant.JOIN_KEYWORD)));
				break;
			}
		}
		return rawResult;
	}
}
