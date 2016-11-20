package edu.dev.entity;

import javax.persistence.*;
import java.util.Date;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/**
 * Created by tengzhao on 11/3/16.
 */
@Entity
@Table(name = "proposal_modification_history")
public class ProposalModificationHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int pid;
    private String subject;
    private String modification;
    @Column(name = "last_updated_at", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedAt;

    public ProposalModificationHistory(int pid, String subject, String modification, Date lastUpdatedAt) {
        this.pid = pid;
        this.subject = subject;
        this.modification = modification;
        this.lastUpdatedAt = lastUpdatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getModification() {
        return modification;
    }

    public void setModification(String modification) {
        this.modification = modification;
    }

    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }

    @Override
    public String toString() {
        return "ProposalModificationHistory{" +
                "id=" + id +
                ", pid=" + pid +
                ", subject='" + subject + '\'' +
                ", modification='" + modification + '\'' +
                ", lastUpdatedAt=" + lastUpdatedAt +
                '}';
    }
}
