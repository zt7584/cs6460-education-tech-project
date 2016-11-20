package edu.dev.entity;

import javax.persistence.*;

/**
 * Created by tengzhao on 11/19/16.
 */
@Entity
@Table(name = "api_usage_statistic_result")
public class ApiUsageStatisticResult {
    public static String TAG = ApiUsageStatisticResult.class.getCanonicalName();

    @Override
    public String toString() {
        return "ApiUsageStatisticResult{" +
                "id=" + id +
                ", apiUsageEntryId=" + apiUsageEntryId +
                ", statisticEntry='" + statisticEntry + '\'' +
                ", result=" + result +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getApiUsageEntryId() {
        return apiUsageEntryId;
    }

    public void setApiUsageEntryId(int apiUsageEntryId) {
        this.apiUsageEntryId = apiUsageEntryId;
    }

    public String getStatisticEntry() {
        return statisticEntry;
    }

    public void setStatisticEntry(String statisticEntry) {
        this.statisticEntry = statisticEntry;
    }

    public long getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public ApiUsageStatisticResult() {
    }

    public ApiUsageStatisticResult(int apiUsageEntryId, String statisticEntry, long result) {
        this.apiUsageEntryId = apiUsageEntryId;
        this.statisticEntry = statisticEntry;
        this.result = result;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected int id;
    @Column(name = "aue_id")
    protected int apiUsageEntryId;
    @Column(name = "statistic_entry")
    private String statisticEntry;
    protected long result;
}
