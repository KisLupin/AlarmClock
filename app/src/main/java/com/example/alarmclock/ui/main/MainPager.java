package com.example.alarmclock.ui.main;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.alarmclock.ui.alarm.FragMainAlarm;
import com.example.alarmclock.ui.stopwatch.StopWatchFrag;
import com.example.alarmclock.ui.timer.TimerFrag;

public class MainPager extends FragmentPagerAdapter {

    public MainPager(FragmentManager manager) {
        super(manager);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new FragMainAlarm();
        }
        if (position == 1){
            return new StopWatchFrag();
        }
        return new TimerFrag();
    }
}
