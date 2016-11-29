package edu.dev.object;

import edu.dev.entity.User;
import edu.dev.entity.UserProposalRelationship;

/**
 * Created by tengzhao on 11/28/16.
 */
public class UserWithRole {

    private User user;
    private int relationship;

    public UserWithRole(User user, int relationship) {
        this.user = user;
        this.relationship = relationship;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getRelationship() {
        return relationship;
    }

    public void setRelationship(int relationship) {
        this.relationship = relationship;
    }
}
