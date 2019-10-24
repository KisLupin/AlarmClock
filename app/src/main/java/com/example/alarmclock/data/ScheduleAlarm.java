package com.example.alarmclock.data;

import com.google.gson.annotations.SerializedName;

public class ScheduleAlarm  {
    @SerializedName("time")
    private String time;
    @SerializedName("boolean")
    private boolean switch_Status;
    @SerializedName("hour")
    private int hour;
    @SerializedName("min")
    private int min;
    @SerializedName("type")
    private String type;

    public ScheduleAlarm(String time, boolean switch_Status, int hour, int min, String type) {
        this.time = time;
        this.switch_Status = switch_Status;
        this.hour = hour;
        this.min = min;
        this.type = type;
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

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
