package edu.dev.controller;

import edu.dev.entity.*;
import edu.dev.repository.ApiUsageStatisticResultRepository;
import edu.dev.service.ApiUsageEntryService;
import edu.dev.service.ApiUsageStatisticResultService;
import edu.dev.service.GradingRubricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by tengzhao on 11/19/16.
 */
@RestController
public class GradingRestController {

    @Autowired
    ApiUsageEntryService apiUsageEntryService;

    @Autowired
    ApiUsageStatisticResultService apiUsageStatisticResultService;

    @Autowired
    GradingRubricService gradingRubricService;

    @RequestMapping(value = "/grading_progress/{id}/{startTime}", method = RequestMethod.GET, produces = "application/json")
    public List<String> getGradingProgress(@PathVariable("id") Integer userId, @PathVariable("startTime") Long startTime) {
        List<String> gradingLogs = new ArrayList<>();
        Date startTimeDate = new Date(startTime);
        List<ApiUsageEntry> apiUsageEntries = apiUsageEntryService.findApiUsageAfter(userId, startTimeDate);
        for (ApiUsageEntry apiUsageEntry : apiUsageEntries) {
            gradingLogs.add("Query: [" + apiUsageEntry.getQuery() + "] is requested to execute at " + apiUsageEntry.getRequestedAt());
        }
        return gradingLogs;
    }

    @RequestMapping(value = "/grading_rubrics", method = RequestMethod.GET, produces = "application/json")
    public List<GradingRubric> getGradingRubrics() {
        return gradingRubricService.findAllGradingRubrics();
    }

    @RequestMapping(value = "/grading_result/{id}/{startTime}", method = RequestMethod.GET, produces = "application/json")
    public List<GradingResultResponse> getGradingResult(@PathVariable("id") Integer userId, @PathVariable("startTime") Long startTime) {
        Date startTimeDate = new Date(startTime);
        Date endTime = new Date();
        List<ApiUsageEntry> apiUsageEntries = apiUsageEntryService.findApiUsageBetween(userId, startTimeDate, endTime);
        List<ApiUsageStatisticResult> apiUsageStatisticResults = null;
        List<GradingRubric> gradingRubrics = gradingRubricService.findAllGradingRubrics();
        Map<String, GradingRubric> gradingRubricHash = new HashMap<>();
        Map<GradingRubric, Boolean> gradingResults = new HashMap<>();
        for (GradingRubric gradingRubric : gradingRubrics) {
            gradingRubricHash.put(gradingRubric.getStatisticEntry(), gradingRubric);
        }
        GradingRubric tempGradingRubric = null;
        for (ApiUsageEntry apiUsageEntry : apiUsageEntries) {
            apiUsageStatisticResults = apiUsageStatisticResultService.findByApiUsageEntryId(apiUsageEntry.getId());
            for (ApiUsageStatisticResult apiUsageStatisticResult : apiUsageStatisticResults) {
                tempGradingRubric = gradingRubricHash.get(apiUsageStatisticResult.getStatisticEntry());
                if (tempGradingRubric != null) {

                    if (tempGradingRubric.getStatisticEntry().equalsIgnoreCase(StatisticEntry.NAME.EXEC_TIME)) {
                        if (gradingResults.get(tempGradingRubric) == null) {
                            gradingResults.put(tempGradingRubric, tempGradingRubric.passOrFail(apiUsageStatisticResult.getResult()));
                        } else {
                            gradingResults.put(tempGradingRubric, gradingResults.get(tempGradingRubric) && tempGradingRubric.passOrFail(apiUsageStatisticResult.getResult()));
                        }
                    }
                    else if (tempGradingRubric.getStatisticEntry().equalsIgnoreCase(StatisticEntry.NAME.NUM_OF_JOIN)) {
                        if (gradingResults.get(tempGradingRubric) == null) {
                            gradingResults.put(tempGradingRubric, tempGradingRubric.passOrFail(apiUsageStatisticResult.getResult()));
                        } else {
                            gradingResults.put(tempGradingRubric, gradingResults.get(tempGradingRubric) || tempGradingRubric.passOrFail(apiUsageStatisticResult.getResult()));
                        }
                    }
                }
            }
        }
        List<GradingResultResponse> gradingResultResponses = new ArrayList<>();
        for (Map.Entry<GradingRubric, Boolean> entry : gradingResults.entrySet()) {
            gradingResultResponses.add(new GradingResultResponse(entry.getKey().getReadableString(), entry.getValue()));
        }
        return gradingResultResponses;
    }
}
