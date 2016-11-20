package edu.dev.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by tengzhao on 11/19/16.
 */
@Entity
@Table(name = "api_usage_entry")
public class ApiUsageEntry {
    public static String TAG = ApiUsageEntry.class.getCanonicalName();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;
    protected int uid;
    protected int db;
    @Column(name = "requested_at", columnDefinition="DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    protected Date requestedAt;
    protected String query;

    public ApiUsageEntry() {
    }

    public ApiUsageEntry(int id, int uid, int db, Date requestedAt, String query) {
        this.id = id;
        this.uid = uid;
        this.db = db;
        this.requestedAt = requestedAt;
        this.query = query;
    }

    public ApiUsageEntry(int uid, int db, Date requestedAt, String query) {
        this.uid = uid;
        this.db = db;
        this.requestedAt = requestedAt;
        this.query = query;
    }

    @Override
    public String toString() {
        return "ApiUsageEntry{" +
                "id=" + id +
                ", uid=" + uid +
                ", db=" + db +
                ", requestedAt=" + requestedAt +
                '}';
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

    public int getDb() {
        return db;
    }

    public void setDb(int db) {
        this.db = db;
    }

    public Date getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(Date requestedAt) {
        this.requestedAt = requestedAt;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
