package edu.dev.repository;

import edu.dev.entity.ApiUsageEntry;
import edu.dev.entity.GradingRubric;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by tengzhao on 11/19/16.
 */
public interface ApiUsageEntryRepository extends CrudRepository<ApiUsageEntry, Integer> {

    List<ApiUsageEntry> findByUidAndRequestedAtBetween(int uid, Date from, Date to);

    List<ApiUsageEntry> findByUidAndRequestedAtAfter(int uid, Date from);
}
