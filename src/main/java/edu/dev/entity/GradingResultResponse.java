package edu.dev.entity;

/**
 * Created by tengzhao on 11/19/16.
 */
public class GradingResultResponse {
    public GradingResultResponse(String gradingRubric, boolean result) {
        this.gradingRubric = gradingRubric;
        this.result = result;
    }

    public String getGradingRubric() {
        return gradingRubric;
    }

    public void setGradingRubric(String gradingRubric) {
        this.gradingRubric = gradingRubric;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "GradingResultResponse{" +
                "gradingRubric='" + gradingRubric + '\'' +
                ", result=" + result +
                '}';
    }

    private String gradingRubric;
    private boolean result;
}
