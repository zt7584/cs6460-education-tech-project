package edu.dev.entity;

/**
 * Created by tengzhao on 11/19/16.
 */
public class ProposalResponse extends Proposal {

    public ProposalResponse(long uid, Proposal proposal) {
        this.uid = uid;
        this.id = proposal.getId();
        this.title = proposal.getTitle();
        this.description = proposal.getDescription();
        this.status = proposal.getStatus();
        this.createdAt = proposal.getCreatedAt();
        this.lastUpdatedAt = proposal.getLastUpdatedAt();
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    private long uid;
}
