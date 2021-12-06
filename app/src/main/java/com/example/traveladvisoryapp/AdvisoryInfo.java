package com.example.traveladvisoryapp;

public class AdvisoryInfo {
    double riskScore;
    String advisoryMessage;
    String Date;

    public AdvisoryInfo(double riskScore, String advisoryMessage, String date) {
        this.riskScore = riskScore;
        this.advisoryMessage = advisoryMessage;
        Date = date;
    }

    public double getRiskScore() {
        return riskScore;
    }

    public void setRiskScore(double riskScore) {
        this.riskScore = riskScore;
    }

    public String getAdvisoryMessage() {
        return advisoryMessage;
    }

    public void setAdvisoryMessage(String advisoryMessage) {
        this.advisoryMessage = advisoryMessage;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
