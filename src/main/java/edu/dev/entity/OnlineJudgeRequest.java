package edu.dev.entity;

/**
 * Created by tengzhao on 11/19/16.
 */
public class OnlineJudgeRequest {
    public OnlineJudgeRequest() {}

    public OnlineJudgeRequest(int database, String query) {
        this.database = database;
        this.query = query;
    }

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "OnlineJudgeRequest{" +
                "database=" + database +
                ", query='" + query + '\'' +
                '}';
    }

    private int database;
    private String query;

}
