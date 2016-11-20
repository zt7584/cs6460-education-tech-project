package edu.dev.entity;

import javax.persistence.*;
import java.util.Date;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/**
 * Created by tengzhao on 11/3/16.
 */
@Entity
@Table(name = "user_progress_update")
public class UserProgressUpdate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int pid;
    private int uid;
    private String progress;
    @Column(name = "created_at", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public UserProgressUpdate(int pid, int uid, String progress, Date createdAt) {
        this.pid = pid;
        this.uid = uid;
        this.progress = progress;
        this.createdAt = createdAt;
    }

    public UserProgressUpdate() {
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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "UserProgressUpdate{" +
                "id=" + id +
                ", pid=" + pid +
                ", uid=" + uid +
                ", progress='" + progress + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
