package edu.dev.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    }

    @Id
    private int id;
    private String title;
    private String description;
    private int status;

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

    @Override
    public String toString() {
        return "Proposal{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
