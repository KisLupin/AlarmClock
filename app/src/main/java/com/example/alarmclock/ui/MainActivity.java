package com.example.alarmclock.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.alarmclock.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.create_alarm).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create_alarm:
                openSetTimer();
                break;
        }
    }

    private void openSetTimer(){
        Intent intent = new Intent(this, TimerPickerActivity.class);
        startActivity(intent);
    }

//    @Override
//    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//        Calendar c = Calendar.getInstance();
//        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
//        c.set(Calendar.MINUTE, minute);
//        c.set(Calendar.SECOND, 0);
//
//        updateTimeText(c);
//        startAlarm(c);
//    }
//
//    private void updateTimeText(Calendar c) {
//        String timeText = "Alarm set for: ";
//        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
//
//        time.setText(timeText);
//    }
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
}
