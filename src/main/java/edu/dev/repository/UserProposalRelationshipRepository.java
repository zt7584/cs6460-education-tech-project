package edu.dev.repository;

import edu.dev.entity.Proposal;
import edu.dev.entity.UserProposalRelationship;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by tengzhao on 10/26/16.
 */
public interface UserProposalRelationshipRepository extends CrudRepository<UserProposalRelationship, String> {

    @Query(value = "SELECT uid, pid, relationship FROM user_proposal_relationship WHERE uid=?1", nativeQuery = true)
    List<UserProposalRelationship> findByUserId(int userId);
}
