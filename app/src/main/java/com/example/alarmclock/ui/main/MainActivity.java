package com.example.alarmclock.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.alarmclock.R;
import com.example.alarmclock.data.ScheduleAlarm;
import com.example.alarmclock.ui.alarm.AlarmSettingActivity;
import com.example.alarmclock.ui.alarm.FragMainAlarm;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements FragMainAlarm.IContent {

    private ViewPager vp;
    private TabLayout tb;
    private ScheduleAlarm scheduleAlarm;
    private FragMainAlarm fragMainAlarm;
    private ImageView alarm;
    private ImageView timer;
    private ImageView stopwatch;
    private View headerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vp = findViewById(R.id.pager);
        tb = findViewById(R.id.tab);
        tb.setupWithViewPager(vp);
        vp.setAdapter(new FragMainPager(getSupportFragmentManager()));
        customTablayout();
        Intent intent = getIntent();
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
//                switch (tab.getPosition()){
//                    case 0:
//                        alarm.setImageResource(R.drawable.alarm_onclick);
//                        timer.setImageResource(R.drawable.timer_noclick);
//                        stopwatch.setImageResource(R.drawable.stopwatch_noclick);
//                        break;
//                    case 1:
//                        alarm.setImageResource(R.drawable.alarm_noclick);
//                        timer.setImageResource(R.drawable.timer_onclick);
//                        stopwatch.setImageResource(R.drawable.stopwatch_noclick);
//                        break;
//                    case 2:
//                        alarm.setImageResource(R.drawable.alarm_noclick);
//                        timer.setImageResource(R.drawable.timer_noclick);
//                        stopwatch.setImageResource(R.drawable.stopwatch_onclick);
//                        break;
//                }
            }
        });
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
    public ScheduleAlarm getContent() {
        return scheduleAlarm;
    }
}


//    private void startAlarm(Calendar c) {
//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(this, AlertReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
//
//        if (c.before(Calendar.getInstance())) {
//            c.add(Calendar.DATE, 1);
//        }
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
//        }
//    }
//
//    private void cancelAlarm() {
//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(this, AlertReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);
//
//        alarmManager.cancel(pendingIntent);
//        time.setText("Alarm canceled");
//    }

