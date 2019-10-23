package com.example.alarmclock.data;

import com.google.gson.annotations.SerializedName;

public class ScheduleAlarm {
    @SerializedName("time")
    private String time;
    @SerializedName("boolean")
    private boolean switch_Status;

    public ScheduleAlarm(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isSwitch_Status() {
        return switch_Status;
    }

    public void setSwitch_Status(boolean switch_Status) {
        this.switch_Status = switch_Status;
    }

}
