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

    public interface Status {
        int PENDING = 0;
        int APPROVED = 1;
        int WITHDRAWN = 2;
        int DECLINED = 3;
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
    @Id
    private long relationship;
    private long status;

    public UserProposalRelationship() {
    }

    public long getRelationship() {
        return relationship;
    }

    public void setRelationship(int relationship) {
        this.relationship = relationship;
    }

    public void setRelationship(long relationship) {
        this.relationship = relationship;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
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
}
