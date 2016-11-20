package edu.dev.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tengzhao on 11/19/16.
 */
public class OnlineJudgeResponse {
    public Object getRawResponse() {
        return rawResponse;
    }

    public void setRawResponse(Object rawResponse) {
        this.rawResponse = rawResponse;
    }

    private Object rawResponse;

    public List<StatisticEntry> getStatisticEntries() {
        if (statisticEntries == null) {
            statisticEntries = new ArrayList<>();
        }
        return statisticEntries;
    }

    public void setStatisticEntries(List<StatisticEntry> statisticEntries) {
        this.statisticEntries = statisticEntries;
    }

    public void addStatisticEntry(StatisticEntry statisticEntry) {
        if (statisticEntries == null) {
            statisticEntries = new ArrayList<>();
        }
        statisticEntries.add(statisticEntry);
    }

    private List<StatisticEntry> statisticEntries;
}
