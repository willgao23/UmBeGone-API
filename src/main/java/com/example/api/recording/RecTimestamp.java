package com.example.api.recording;

public class RecTimestamp {
    private double startTime;
    private double endTime;

    public RecTimestamp(double start, double end) {
        this.startTime = start;
        this.endTime = end;
    }

    public double getStartTime() {
        return startTime;
    }

    public double getEndTime() {
        return endTime;
    }

    public void setStartTime(double start) {
        this.startTime = start;
    }

    public void setEndTime(double end) {
        this.endTime = end;
    }
}
