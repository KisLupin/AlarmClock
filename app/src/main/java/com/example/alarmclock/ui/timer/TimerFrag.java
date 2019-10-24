package com.example.alarmclock.ui.timer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.alarmclock.R;
import com.example.alarmclock.ui.main.MainPager;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TimerFrag extends Fragment implements View.OnClickListener {

    private ImageView start;
    private TextView hour;
    private TextView min;
    private TextView second;
    private int length = 0;
    private List<Integer> timer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_timer, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        timer = new ArrayList<>();
        init(view);
    }

    private void init(View view){
        view.findViewById(R.id.num0).setOnClickListener(this);
        view.findViewById(R.id.num1).setOnClickListener(this);
        view.findViewById(R.id.num2).setOnClickListener(this);
        view.findViewById(R.id.num3).setOnClickListener(this);
        view.findViewById(R.id.num4).setOnClickListener(this);
        view.findViewById(R.id.num5).setOnClickListener(this);
        view.findViewById(R.id.num6).setOnClickListener(this);
        view.findViewById(R.id.num7).setOnClickListener(this);
        view.findViewById(R.id.num8).setOnClickListener(this);
        view.findViewById(R.id.num9).setOnClickListener(this);
        view.findViewById(R.id.delete_text_time).setOnClickListener(this);
        view.findViewById(R.id.start_timer).setOnClickListener(this);
        hour = view.findViewById(R.id.hour);
        min = view.findViewById(R.id.minute);
        second = view.findViewById(R.id.second);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.num0:
                if (length == 0){
                    return;
                }
                updateTimerTimeText(0);
                break;
            case R.id.num1:
                updateTimerTimeText(1);
                break;
            case R.id.num2:
                updateTimerTimeText(2);
                break;
            case R.id.num3:
                updateTimerTimeText(3);
                break;
            case R.id.num4:
                updateTimerTimeText(4);
                break;
            case R.id.num5:
                updateTimerTimeText(5);
                break;
            case R.id.num6:
                updateTimerTimeText(6);
                break;
            case R.id.num7:
                updateTimerTimeText(7);
                break;
            case R.id.num8:
                updateTimerTimeText(8);
                break;
            case R.id.num9:
                updateTimerTimeText(9);
                break;
            case R.id.delete_text_time:
                if (length == 0){
                    return;
                }
                length --;
                timer.remove(timer.size()-1);
                updateAfterRemove();
                break;
            case R.id.start_timer:
                getFragmentManager().beginTransaction().replace(R.id.control, new TimerRunFrag()).commit();
                break;
        }
    }

    private void updateTimerTimeText(int select){
        length++;
        if (length > 6){
            length = 6;
            return;
        }
        timer.add(select);
        switch (length){
            case 1:
                second.setText("0"+timer.get(0));
                break;
            case 2:
                second.setText(timer.get(0)+""+timer.get(1));
                break;
            case 3:
                min.setText("0"+timer.get(0));
                second.setText(timer.get(1)+""+timer.get(2));
                break;
            case 4:
                min.setText(timer.get(0)+""+timer.get(1));
                second.setText(timer.get(2)+""+timer.get(3));
                break;
            case 5:
                hour.setText("0"+timer.get(0));
                min.setText(timer.get(1)+""+timer.get(2));
                second.setText(timer.get(3)+""+timer.get(4));
                break;
            case 6:
                hour.setText(timer.get(0)+""+timer.get(1));
                min.setText(timer.get(2)+""+timer.get(3));
                second.setText(timer.get(4)+""+timer.get(5));
                break;
        }
    }

    private void updateAfterRemove(){
        switch (length){
            case 0:
                second.setText("00");
                break;
            case 1:
                second.setText("0"+timer.get(0));
                break;
            case 2:
                min.setText("00");
                second.setText(timer.get(0)+""+timer.get(1));
                break;
            case 3:
                min.setText("0"+timer.get(0));
                second.setText(timer.get(1)+""+timer.get(2));
                break;
            case 4:
                hour.setText("00");
                min.setText(timer.get(0)+""+timer.get(1));
                second.setText(timer.get(2)+""+timer.get(3));
                break;
            case 5:
                hour.setText("0"+timer.get(0));
                min.setText(timer.get(1)+""+timer.get(2));
                second.setText(timer.get(3)+""+timer.get(4));
                break;
            case 6:
                hour.setText(timer.get(0)+""+timer.get(1));
                min.setText(timer.get(2)+""+timer.get(3));
                second.setText(timer.get(4)+""+timer.get(5));
                break;
        }
    }
}
