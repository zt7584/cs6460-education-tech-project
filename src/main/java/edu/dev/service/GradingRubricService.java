package edu.dev.service;

import edu.dev.entity.GradingRubric;
import edu.dev.entity.User;
import edu.dev.repository.GradingRubricRepository;
import edu.dev.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tengzhao on 11/19/16.
 */
@Service
public class GradingRubricService {

    @Autowired
    GradingRubricRepository gradingRubricRepository;

    @Transactional
    public void saveGradingRubric(GradingRubric gradingRubric) {
        gradingRubricRepository.save(gradingRubric);
    }

    @Transactional
    public List<GradingRubric> findAllGradingRubrics() {
        return gradingRubricRepository.findAllGradingRubric();
    }
}
