package edu.dev.service;

import edu.dev.entity.ApiUsageEntry;
import edu.dev.repository.ApiUsageEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by tengzhao on 11/19/16.
 */
@Service
public class ApiUsageEntryService {

    @Autowired
    ApiUsageEntryRepository apiUsageEntryRepository;

    public ApiUsageEntry saveApiUsageEntry(ApiUsageEntry apiUsageEntry) {
        return apiUsageEntryRepository.save(apiUsageEntry);
    }

    public List<ApiUsageEntry> findApiUsageBetween(int uid, Date from, Date to) {
        return apiUsageEntryRepository.findByUidAndRequestedAtBetween(uid, from, to);
    }

    public List<ApiUsageEntry> findApiUsageAfter(int uid, Date from) {
        return apiUsageEntryRepository.findByUidAndRequestedAtAfter(uid, from);
    }
}
