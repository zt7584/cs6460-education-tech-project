package edu.dev.repository;

import edu.dev.entity.ProposalModificationHistory;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by tengzhao on 11/3/16.
 */
public interface ProposalModificationHistoryRepository extends CrudRepository<ProposalModificationHistory, String> {
}
