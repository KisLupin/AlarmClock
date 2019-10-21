package com.example.alarmclock.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.alarmclock.R;
import com.example.alarmclock.ui.MainActivity;

public class TimerPickerActivity extends AppCompatActivity implements View.OnClickListener {

    private String hours;
    private String minutes;
    private TimePicker timerPicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timepicker);
        timerPicker = findViewById(R.id.timer_picker);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            hours = timerPicker.getHour()+"";
            minutes = timerPicker.getMinute()+"";
        }
        findViewById(R.id.cancel).setOnClickListener(this);
        findViewById(R.id.add_alarm).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_alarm:
                addSuccess(hours,minutes);
                break;
            case R.id.cancel:
                openMain();
                break;
        }
    }

    private void addSuccess(String hours, String minutes){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("HOUR",hours);
        intent.putExtra("MIN",minutes);
        startActivity(intent);
    }

    private void openMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
