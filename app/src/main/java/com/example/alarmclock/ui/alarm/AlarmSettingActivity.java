package com.example.alarmclock.ui.alarm;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.alarmclock.R;
import com.example.alarmclock.data.ScheduleAlarm;
import com.example.alarmclock.ui.main.MainActivity;
import com.example.alarmclock.utils.ShareUtilsAlarm;

import java.util.Comparator;

import static java.util.Collections.sort;

public class AlarmSettingActivity extends AppCompatActivity implements View.OnClickListener {

    private TimePicker timerPicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timepicker);
        timerPicker = findViewById(R.id.timer_picker);
        findViewById(R.id.cancel).setOnClickListener(this);
        findViewById(R.id.add_alarm).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_alarm:
                addSuccess();
                break;
            case R.id.cancel:
                openMain();
                break;
        }
    }

    private void addSuccess(){
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            String time = timerPicker.getHour()+":"+timerPicker.getMinute();
            String am_pm;
            if (timerPicker.getHour() >= 12){
                am_pm = "PM";
            }
            else {
                am_pm = "AM";
            }
            for (int i = 0; i < FragMainAlarm.alarmList.size(); i++) {
               if ( FragMainAlarm.alarmList.get(i).getTime().equals(time)){
                    Toast.makeText(this,"This time is set",Toast.LENGTH_LONG);
                    return;
                }
            }
            FragMainAlarm.alarmList.add(new ScheduleAlarm(time,true,timerPicker.getHour(),timerPicker.getMinute(),am_pm));
            sortTime();
            ShareUtilsAlarm.saveAlarm(this,FragMainAlarm.alarmList);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    private void sortTime() {
        if (FragMainAlarm.alarmList == null){
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            sort(FragMainAlarm.alarmList, (Comparator.<ScheduleAlarm>
                    comparingInt(alarm -> alarm.getHour())
                    .thenComparingInt(alarm -> alarm.getMin())));
        }
    }

    private void openMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
