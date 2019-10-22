package com.example.alarmclock.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.alarmclock.data.ScheduleAlarm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ShareUtilsAlarm {

    public static void saveAlarm(Context context, List<ScheduleAlarm> scheduleAlarm) {
        SharedPreferences sh = context.getSharedPreferences("SHARE", Context.MODE_PRIVATE);
        Type type =  new TypeToken<List<ScheduleAlarm>>(){}.getType();
        String json = new Gson().toJson(scheduleAlarm,type);
        sh.edit().putString("ALARM", json)
                .apply();
    }

    public static List<ScheduleAlarm> getAllAlarm(Context context){
        String json = context.getSharedPreferences("SHARE", Context.MODE_PRIVATE).getString("ALARM",null);
        Type type =  new TypeToken<List<ScheduleAlarm>>(){}.getType();
        List<ScheduleAlarm> allAlarm =  new Gson().fromJson(json, type);
        return allAlarm;
    }

    public static void clearAll(Context context){
        context.getSharedPreferences("SHARE",Context.MODE_PRIVATE)
                .edit().putString("ALARM", null).apply();
    }
}
