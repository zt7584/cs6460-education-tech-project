package edu.dev.service;

import edu.dev.entity.ApiUsageEntry;
import edu.dev.entity.ApiUsageStatisticResult;
import edu.dev.repository.ApiUsageEntryRepository;
import edu.dev.repository.ApiUsageStatisticResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by tengzhao on 11/19/16.
 */
@Service
public class ApiUsageStatisticResultService {

    @Autowired
    ApiUsageStatisticResultRepository apiUsageStatisticResultRepository;

    public ApiUsageStatisticResult saveApiUsageStatisticResult(ApiUsageStatisticResult apiUsageStatisticResult) {
        return apiUsageStatisticResultRepository.save(apiUsageStatisticResult);
    }

    public List<ApiUsageStatisticResult> findByApiUsageEntryId(Integer id) {
        return apiUsageStatisticResultRepository.findByApiUsageEntryId(id);
    }
}
