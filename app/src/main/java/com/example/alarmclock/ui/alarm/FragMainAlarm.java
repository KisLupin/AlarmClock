package com.example.alarmclock.ui.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alarmclock.R;
import com.example.alarmclock.data.ScheduleAlarm;
import com.example.alarmclock.utils.AlarmSoundActive;
import com.example.alarmclock.utils.AlertReceiver;
import com.example.alarmclock.utils.ShareUtilsAlarm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static java.util.Collections.sort;

public class FragMainAlarm extends Fragment implements AlarmAdapter.IAlarm {

    private RecyclerView rc;
    public static List<ScheduleAlarm> alarmList = new ArrayList<>();
    private AlarmAdapter alarmAdapter;
    private boolean isClick;
    private int count = 0;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private Calendar calendar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        alarmList = ShareUtilsAlarm.getAllAlarm(getContext());
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        return inflater.inflate(R.layout.frag_main_alarm, container ,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc = view.findViewById(R.id.rc_alarm);
        rc.setLayoutManager(new LinearLayoutManager(getContext()));
        alarmAdapter = new AlarmAdapter(this);
        rc.setAdapter(alarmAdapter);
        view.findViewById(R.id.create_alarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSetTimer();
            }
        });
        alarmList = ShareUtilsAlarm.getAllAlarm(getContext());
        view.findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if (count == 1){
                    isClick = true;
                    alarmAdapter.notifyDataSetChanged();
                }else {
                    isClick =false;
                    count = 0;
                    alarmAdapter.notifyDataSetChanged();
                }
            }
        });
        alarmInstance();
    }

    private void openSetTimer(){
        Intent intent = new Intent(getContext(), AlarmSettingActivity.class);
        startActivity(intent);
    }

    @Override
    public int getCount() {
        if (alarmList == null){
            return 0;
        }
        return alarmList.size();
    }

    @Override
    public ScheduleAlarm getItem(int pos) {
        return alarmList.get(pos);
    }

    @Override
    public boolean editClick() {
        return isClick;
    }

    @Override
    public void onClick(int pos) {
        removeAlarm(pos);
    }

    @Override
    public void switchIsClick(boolean status, int pos) {
        alarmList.get(pos).setSwitch_Status(status);
        ShareUtilsAlarm.saveAlarm(getContext(), alarmList);
    }

    private void removeAlarm(int pos){
        alarmList.remove(pos);
        ShareUtilsAlarm.clearAll(getContext());
        ShareUtilsAlarm.saveAlarm(getContext(), alarmList);
        alarmInstance();
        alarmAdapter.notifyDataSetChanged();
    }

    private void alarmInstance(){
        if (alarmList == null) alarmList = new ArrayList<>();
        for (int i = 0; i < alarmList.size(); i++) {
            if (alarmList.get(i).isSwitch_Status()){
                Intent intent = new Intent(getContext(), AlertReceiver.class);
                intent.putExtra("ID",i);
                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, alarmList.get(i).getHour());
                calendar.set(Calendar.MINUTE, alarmList.get(i).getMin() -1);
                calendar.set(Calendar.MILLISECOND, 0);
                pendingIntent = PendingIntent.getBroadcast(getContext(), i, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
            }
        }
    }

}
