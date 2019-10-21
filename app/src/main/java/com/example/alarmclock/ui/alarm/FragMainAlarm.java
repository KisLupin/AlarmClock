package com.example.alarmclock.ui.alarm;

import android.content.Intent;
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
import com.example.alarmclock.ui.main.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class FragMainAlarm extends Fragment implements AlarmAdapter.IAlarm {

    private RecyclerView rc;
    private List<ScheduleAlarm> alarmList;
    private AlarmAdapter alarmAdapter;
    private IContent content;

    public FragMainAlarm(IContent content) {
        this.content = content;
    }

    public FragMainAlarm() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
        init();
    }

    @Override
    public void onResume() {
        super.onResume();
//        init();
    }

    public interface IContent{
        ScheduleAlarm getContent();
    }

    private void init(){
        alarmList = new ArrayList<>();
//        ScheduleAlarm scheduleAlarm = content.getContent();
//        alarmList.add(scheduleAlarm);
        alarmList.add(new ScheduleAlarm("7:00","AM"));
        alarmList.add(new ScheduleAlarm("8:30","PM"));
        alarmAdapter.notifyDataSetChanged();
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
    public void onClick(int pos) {

    }
}
