package edu.dev.object;

import edu.dev.entity.Proposal;
import edu.dev.entity.User;
import edu.dev.entity.UserProposalRelationship;

/**
 * Created by tengzhao on 11/28/16.
 */
public class RequestWrapper {

    private User user;
    private Proposal proposal;
    private UserProposalRelationship relationship;

    public RequestWrapper(User user, Proposal proposal, UserProposalRelationship relationship) {
        this.user = user;
        this.proposal = proposal;
        this.relationship = relationship;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    public UserProposalRelationship getRelationship() {
        return relationship;
    }

    public void setRelationship(UserProposalRelationship relationship) {
        this.relationship = relationship;
    }
}
