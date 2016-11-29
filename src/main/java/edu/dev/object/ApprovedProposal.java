package edu.dev.object;

import edu.dev.entity.Proposal;
import edu.dev.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tengzhao on 11/28/16.
 */
public class ApprovedProposal {

    private Proposal proposal;
    private List<UserWithRole> members;

    public ApprovedProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    public ApprovedProposal(Proposal proposal, List<UserWithRole> members) {
        this.proposal = proposal;
        this.members = members;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    public List<UserWithRole> getMembers() {
        return members;
    }

    public void setMembers(List<UserWithRole> members) {
        this.members = members;
    }

    public void addMember(UserWithRole user) {
        if (members == null) {
            members = new ArrayList<>();
        }
        members.add(user);
    }
}
