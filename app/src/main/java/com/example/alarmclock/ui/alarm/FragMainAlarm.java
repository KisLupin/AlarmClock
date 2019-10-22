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
import com.example.alarmclock.utils.ShareUtilsAlarm;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FragMainAlarm extends Fragment implements AlarmAdapter.IAlarm {

    private RecyclerView rc;
    private static List<ScheduleAlarm> alarmList = new ArrayList<>();
    private AlarmAdapter alarmAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        alarmList = ShareUtilsAlarm.getAllAlarm(getContext());
        super.onCreate(savedInstanceState);
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
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
        ShareUtilsAlarm.saveAlarm(getContext(), alarmList);
    }

    private void openSetTimer(){
        Intent intent = new Intent(getContext(), AlarmSettingActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

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

    private void getData(){
        if (getActivity().getIntent().getExtras() == null){
            return;
        }
        String hour = getActivity().getIntent().getExtras().getString("HOUR");
        String min = getActivity().getIntent().getExtras().getString("MIN");
        String text = hour +":"+ min;
        alarmList.add(new ScheduleAlarm(text));
        alarmAdapter.notifyDataSetChanged();
    }

    private void removeAlarm(int pos){
        alarmList.remove(pos);
        ShareUtilsAlarm.clearAll(getContext());
        ShareUtilsAlarm.saveAlarm(getContext(), alarmList);
        alarmAdapter.notifyDataSetChanged();
    }

    private void sortTime(){

    }
}
