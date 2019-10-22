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
import com.example.alarmclock.utils.AlertReceiver;
import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private TabLayout tb;
    private ImageView alarm;
    private ImageView timer;
    private ImageView stopwatch;
    private View headerView;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Calendar calendar;

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
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
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
        Intent intent2 = new Intent(this,AlertReceiver.class);
        Intent intent = getIntent();
        calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, intent.getIntExtra("hrn",0));
        calendar.set(Calendar.MINUTE, intent.getIntExtra("minn",0));
        calendar.set(Calendar.MILLISECOND, 0);
        pendingIntent = PendingIntent.getBroadcast(this,0,intent2,PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
    }
}


