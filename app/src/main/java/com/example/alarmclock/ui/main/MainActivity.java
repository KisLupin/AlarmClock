package com.example.alarmclock.ui.main;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.alarmclock.R;
import com.example.alarmclock.data.ScheduleAlarm;
import com.example.alarmclock.utils.AlarmSoundActive;
import com.example.alarmclock.utils.AlertReceiver;
import com.example.alarmclock.utils.ShareUtilsAlarm;
import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private TabLayout tb;
    private ImageView alarm;
    private ImageView timer;
    private ImageView stopwatch;
    private View headerView;
    public static int id;
    private List<ScheduleAlarm> alarmList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = findViewById(R.id.pager);
        tb = findViewById(R.id.tab);
        tb.setupWithViewPager(vp);
        vp.setAdapter(new MainPager(getSupportFragmentManager()));
        customTablayout();
        tb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        alarm.setImageResource(R.drawable.alarm_onclick);
                        timer.setImageResource(R.drawable.timer_noclick);
                        stopwatch.setImageResource(R.drawable.stopwatch_noclick);
                        break;
                    case 1:
                        alarm.setImageResource(R.drawable.alarm_noclick);
                        timer.setImageResource(R.drawable.timer_onclick);
                        stopwatch.setImageResource(R.drawable.stopwatch_noclick);
                        break;
                    case 2:
                        alarm.setImageResource(R.drawable.alarm_noclick);
                        timer.setImageResource(R.drawable.timer_noclick);
                        stopwatch.setImageResource(R.drawable.stopwatch_onclick);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        alarmList = ShareUtilsAlarm.getAllAlarm(this);
        stopService(new Intent(this, AlarmSoundActive.class));
        if (id != 100){
            if (alarmList.size() != 0){
                cancelAlarm(id);
            }
        }
    }

    private void customTablayout(){
        headerView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.custom_tablayout, null, false);

        alarm =  headerView.findViewById(R.id.alarm_is_click);
        timer =  headerView.findViewById(R.id.timer_is_click);
        stopwatch =  headerView.findViewById(R.id.stopwatch_is_click);

        tb.getTabAt(0).setCustomView(alarm);
        tb.getTabAt(1).setCustomView(timer);
        tb.getTabAt(2).setCustomView(stopwatch);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void cancelAlarm(int id){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmList.get(id).setSwitch_Status(false);
        ShareUtilsAlarm.saveAlarm(this, alarmList);
        alarmManager.cancel(pendingIntent);
    }
}


