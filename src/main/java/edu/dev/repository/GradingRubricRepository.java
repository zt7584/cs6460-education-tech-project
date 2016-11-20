package edu.dev.repository;

import edu.dev.entity.GradingRubric;
import edu.dev.entity.Proposal;
import edu.dev.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by tengzhao on 11/19/16.
 */
public interface GradingRubricRepository extends CrudRepository<GradingRubric, Integer> {

    @Query(value = "SELECT id, name, threshold, operator, statistic_entry FROM grading_rubric", nativeQuery = true)
    List<GradingRubric> findAllGradingRubric();
}
