package edu.dev.entity;

import javax.persistence.*;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Created by tengzhao on 11/19/16.
 */
@Entity
@Table(name = "grading_rubric")
public class GradingRubric {
    private static final ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
    private static final ScriptEngine engine = scriptEngineManager.getEngineByName("js");

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public GradingRubric() {}

    public GradingRubric(String name, int threshold, String operator, String statisticEntry) {
        this.name = name;
        this.threshold = threshold;
        this.operator = operator;
        this.statisticEntry = statisticEntry;
    }

    public boolean passOrFail(Object input) {
        try {
            String expr = ((long) input) + " " + operator + " " + threshold;
            Object result = engine.eval(expr);
            return (boolean) result;
        } catch (Throwable t) {
        }
        return false;
    }

    @Override
    public String toString() {
        return "GradingRubric{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", threshold=" + threshold +
                ", operator='" + operator + '\'' +
                '}';
    }

    public String getReadableString() {
        return "Grading Rubric [\"" + name + "\" " + operator + " " + threshold + "]";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private int threshold;
    private String operator;

    public String getStatisticEntry() {
        return statisticEntry;
    }

    public void setStatisticEntry(String statisticEntry) {
        this.statisticEntry = statisticEntry;
    }

    @Column(name = "statistic_entry")
    private String statisticEntry;
}
