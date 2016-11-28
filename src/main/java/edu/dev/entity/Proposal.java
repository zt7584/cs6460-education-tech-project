package edu.dev.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.sun.tools.doclets.formats.html.markup.HtmlStyle.title;

/**
 * Created by tengzhao on 10/26/16.
 */
@Entity
@Table(name = "proposal")
public class Proposal {
    public static String TAG = Proposal.class.getCanonicalName();

    public interface Status {
        int PENDING = 0;
        int APPROVED = 1;
        int WITHDRAWN = 2;
        int DECLINED = 3;
    }

    public Proposal(int id, String title, String description, int status, List<UserProposalRelationship> users, Date createdAt, Date lastUpdatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
//        this.users = users;
        this.createdAt = createdAt;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    @Override
    public String toString() {
        return "Proposal{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
//                ", users=" + users +
                ", createdAt=" + createdAt +
                ", lastUpdatedAt=" + lastUpdatedAt +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;
    protected String title;
    protected String description;
    protected int status;

    @Column(name = "created_at", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdAt;

    @Column(name = "last_updated_at", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastUpdatedAt;

    public Proposal() {
    }

    public Proposal(int id, String title, String description, int status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

//    public List<UserProposalRelationship> getUsers() {
//        return users;
//    }
//
//    public void setUsers(List<UserProposalRelationship> users) {
//        this.users = users;
//    }

//    public void addUser(User user, boolean isCreator) {
//        UserProposalRelationship userProposalRelationship = new UserProposalRelationship();
//        userProposalRelationship.setRelationship(UserProposalRelationship.Relationship.CREATOR);
////        userProposalRelationship.setUser(user);
////        userProposalRelationship.setProposal(this);
//        if (isCreator) {
//            userProposalRelationship.setRelationship(UserProposalRelationship.Relationship.CREATOR);
//        } else {
//            userProposalRelationship.setRelationship(UserProposalRelationship.Relationship.MEMBER);
//        }
//        this.users.add(userProposalRelationship);
////        user.getProposals().add(userProposalRelationship);
//    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

}
