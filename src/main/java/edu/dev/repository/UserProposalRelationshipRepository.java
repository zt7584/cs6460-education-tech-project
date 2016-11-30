package edu.dev.repository;

import edu.dev.entity.Proposal;
import edu.dev.entity.UserProposalRelationship;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by tengzhao on 10/26/16.
 */
public interface UserProposalRelationshipRepository extends CrudRepository<UserProposalRelationship, String> {

    @Query(value = "SELECT uid, pid, relationship, status FROM user_proposal_relationship WHERE uid=?1 AND relationship=0", nativeQuery = true)
    List<UserProposalRelationship> findProposalsOwnedByUserId(int userId);

    @Query(value = "SELECT uid, pid, relationship, status FROM user_proposal_relationship WHERE uid<>?1 AND relationship=0", nativeQuery = true)
    List<UserProposalRelationship> findProposalsNotOwnedByUserId(int userId);

    @Query(value = "SELECT uid, pid, relationship, status FROM user_proposal_relationship WHERE uid=?1 AND relationship<>0", nativeQuery = true)
    List<UserProposalRelationship> findRequestsMadeByUserId(int userId);

    @Query(value = "SELECT uid, pid, relationship, status FROM user_proposal_relationship WHERE uid<>?1 AND relationship<>0", nativeQuery = true)
    List<UserProposalRelationship> findRequestsNotMadeByUserId(int userId);

    @Query(value = "SELECT uid, pid, relationship, status FROM user_proposal_relationship WHERE pid=?1 AND relationship=0 AND status=1", nativeQuery = true)
    List<UserProposalRelationship> findOwnerOfApprovedProposal(int proposalId);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE user_proposal_relationship SET status=?1 WHERE uid=?2 AND pid=?3 AND relationship=?4", nativeQuery = true)
    void updateUserProposalRelationshipStatus(int status, int uid, int pid, int relationship);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE user_proposal_relationship SET status=?1 WHERE pid=?2", nativeQuery = true)
    void withdrawProposalRelationshipStatus(int status, int pid);
}
