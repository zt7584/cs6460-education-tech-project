package edu.dev.entity;

import java.io.Serializable;

/**
 * Created by tengzhao on 10/26/16.
 */
public class UserProposalRelationshipId implements Serializable {
    private long uid;
    private long pid;

    public long getRelationship() {
        return relationship;
    }

    public void setRelationship(long relationship) {
        this.relationship = relationship;
    }

    private long relationship;

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

    public int hashCode() {
        return (int)(uid + pid + (uid * pid) + (uid - pid) + relationship);
    }

    public boolean equals(Object object) {
        if (object instanceof UserProposalRelationshipId) {
            UserProposalRelationshipId otherId = (UserProposalRelationshipId) object;
            return (otherId.uid == this.uid) && (otherId.pid == this.pid)
                    && (otherId.relationship == this.relationship);
        }
        return false;
    }
}
