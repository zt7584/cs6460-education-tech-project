package edu.dev.repository;

import edu.dev.entity.Proposal;
import edu.dev.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Created by tengzhao on 10/26/16.
 */
public interface ProposalRepository extends CrudRepository<Proposal, String> {

    @Query(value = "SELECT id, title, description, status, created_at, last_updated_at FROM proposal WHERE id=?1", nativeQuery = true)
    List<Proposal> findById(long proposalId);

    @Modifying(clearAutomatically = true)
//    @Query(value = "UPDATE proposal SET status=?1, last_updated_at=?2 WHERE id=?3", nativeQuery = true)
    @Query(value = "UPDATE proposal SET status=?1 WHERE id=?2", nativeQuery = true)
    void updateProposalStatus(int status, int pid);
}
