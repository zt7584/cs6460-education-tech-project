package edu.dev.object;

import edu.dev.entity.Proposal;
import edu.dev.entity.User;

/**
 * Created by tengzhao on 11/28/16.
 */
public class ProposalWithUser {

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ProposalWithUser(Proposal proposal, User user) {
        this.proposal = proposal;
        this.user = user;
    }

    private Proposal proposal;
    private User user;
}
