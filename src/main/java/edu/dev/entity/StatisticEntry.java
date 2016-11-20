package edu.dev.entity;

import java.util.List;

import static edu.dev.entity.StatisticEntry.NAME.EXEC_TIME;

/**
 * Created by tengzhao on 11/19/16.
 */
public class StatisticEntry {

    public interface NAME {
        String EXEC_TIME = "Execution Time";
        String NUM_OF_JOIN = "Number of Join";
    }

    public static final String[] STATISTIC_ENTRY_NAMES = {NAME.EXEC_TIME, NAME.NUM_OF_JOIN};

    @Override
    public String toString() {
        return "StatisticEntry{" +
                "name='" + name + '\'' +
                ", numeric=" + numeric +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getNumeric() {
        return numeric;
    }

    public void setNumeric(long numeric) {
        this.numeric = numeric;
    }

    private String name;
    private long numeric;

    public StatisticEntry(String name, long numeric, String unit) {
        this.name = name;
        this.numeric = numeric;
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    private String unit;
}
