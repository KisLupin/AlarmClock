package com.example.alarmclock.data;

public class ScheduleAlarm {
    private String time;
    private String tyle;

    public ScheduleAlarm(String time, String tyle) {
        this.time = time;
        this.tyle = tyle;
    }

    public String getTyle() {
        return tyle;
    }

    public void setTyle(String tyle) {
        this.tyle = tyle;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
