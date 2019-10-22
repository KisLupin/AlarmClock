package com.example.alarmclock.ui.alarm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alarmclock.R;
import com.example.alarmclock.data.ScheduleAlarm;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ViewHolder> {

    private IAlarm alarm;

    public AlarmAdapter(IAlarm alarm) {
        this.alarm = alarm;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_alarm, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        ScheduleAlarm scheduleAlarm = alarm.getItem(position);
        holder.time.setText(scheduleAlarm.getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarm.onClick(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return alarm.getCount();
    }

    public interface IAlarm{
        int getCount();
        ScheduleAlarm getItem(int pos);
        void onClick(int pos);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView time;
        private TextView form;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time_is_set);
            form = itemView.findViewById(R.id.style_time);
        }
    }
}
