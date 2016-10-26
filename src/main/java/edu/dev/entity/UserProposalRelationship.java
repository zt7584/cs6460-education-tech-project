package edu.dev.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by tengzhao on 10/26/16.
 */
@Entity
@Table(name = "user_proposal_relationship")
public class UserProposalRelationship {
    public static String TAG = UserProposalRelationship.class.getCanonicalName();

    public interface Relationship {
        int CREATOR = 0;
        int MEMBER = 1;
    }

    @Override
    public String toString() {
        return "UserProposalRelationship{" +
                "id=" + id +
                ", uid=" + uid +
                ", pid=" + pid +
                ", relationship=" + relationship +
                '}';
    }

    @Id
    private int id;
    private int uid;
    private int pid;
    private int relationship;

    public UserProposalRelationship(int id, int uid, int pid, int relationship) {
        this.id = id;
        this.uid = uid;
        this.pid = pid;
        this.relationship = relationship;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getRelationship() {
        return relationship;
    }

    public void setRelationship(int relationship) {
        this.relationship = relationship;
    }

}
