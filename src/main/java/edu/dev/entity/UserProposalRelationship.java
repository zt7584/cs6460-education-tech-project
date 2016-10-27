package edu.dev.entity;

import javax.persistence.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/**
 * Created by tengzhao on 10/26/16.
 */
@Entity
@Table(name = "user_proposal_relationship")
@IdClass(UserProposalRelationshipId.class)
public class UserProposalRelationship {
    public static String TAG = UserProposalRelationship.class.getCanonicalName();

    public interface Relationship {
        int CREATOR = 0;
        int MEMBER = 1;
    }

    @Override
    public String toString() {
        return "UserProposalRelationship{" +
                "uid=" + uid +
                ", pid=" + pid +
                ", relationship=" + relationship +
                '}';
    }

    @Id
    private long uid;
    @Id
    private long pid;

    public long getRelationship() {
        return relationship;
    }

    public void setRelationship(int relationship) {
        this.relationship = relationship;
    }

    //    @ManyToOne
//    @PrimaryKeyJoinColumn(name="uid", referencedColumnName="id")
//    private User user;
//    @ManyToOne
//    @PrimaryKeyJoinColumn(name="pid", referencedColumnName="id")
//    private Proposal proposal;
    @Id
    private long relationship;

    public UserProposalRelationship() {
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        this.pid = pid;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public Proposal getProposal() {
//        return proposal;
//    }
//
//    public void setProposal(Proposal proposal) {
//        this.proposal = proposal;
//    }
//
//    public int getRelationship() {
//        return relationship;
//    }
//
//    public void setRelationship(int relationship) {
//        this.relationship = relationship;
//    }
}
