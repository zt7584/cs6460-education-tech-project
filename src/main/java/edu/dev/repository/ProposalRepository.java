package edu.dev.repository;

import edu.dev.entity.Proposal;
import edu.dev.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by tengzhao on 10/26/16.
 */
public interface ProposalRepository extends CrudRepository<Proposal, String> {

    @Query(value = "SELECT id, title, description, status, created_at, last_updated_at FROM proposal WHERE id=?1", nativeQuery = true)
    List<Proposal> findById(long proposalId);
}
