package com.example.alarmclock.ui.alarm;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
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
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        ScheduleAlarm scheduleAlarm = alarm.getItem(position);
        holder.time.setText(scheduleAlarm.getTime());

        if (alarm.editClick()){
            holder.im.setVisibility(View.VISIBLE);
        }else  holder.im.setVisibility(View.GONE);
        holder.im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarm.onClick(position);
            }
        });

        holder.aSwitch.setChecked(scheduleAlarm.isSwitch_Status());
        holder.aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                alarm.switchIsClick(isChecked, holder.getAdapterPosition());
            }
        });

        holder.type.setText(scheduleAlarm.getType());
    }

    @Override
    public int getItemCount() {
        return alarm.getCount();
    }

    public interface IAlarm{
        int getCount();
        ScheduleAlarm getItem(int pos);
        boolean editClick();
        void onClick(int pos);
        void switchIsClick(boolean status, int pos);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView time;
        private ImageView im;
        private Switch aSwitch;
        private TextView type;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.time_is_set);
            im = itemView.findViewById(R.id.show_delete);
            aSwitch = itemView.findViewById(R.id.switch_on_off);
            type = itemView.findViewById(R.id.type);
        }
    }
}
