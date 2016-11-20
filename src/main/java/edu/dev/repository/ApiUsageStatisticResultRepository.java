package edu.dev.repository;

import edu.dev.entity.ApiUsageEntry;
import edu.dev.entity.ApiUsageStatisticResult;
import edu.dev.entity.GradingRubric;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by tengzhao on 11/19/16.
 */
public interface ApiUsageStatisticResultRepository extends CrudRepository<ApiUsageStatisticResult, Integer> {

    List<ApiUsageStatisticResult> findByApiUsageEntryId(Integer id);
}
