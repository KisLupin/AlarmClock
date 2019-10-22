package com.example.alarmclock.data;

import com.google.gson.annotations.SerializedName;

public class ScheduleAlarm {
    @SerializedName("time")
    private String time;
    public ScheduleAlarm(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
