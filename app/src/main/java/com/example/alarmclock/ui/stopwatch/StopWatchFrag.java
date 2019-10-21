package com.example.alarmclock.ui.stopwatch;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.alarmclock.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StopWatchFrag extends Fragment {

    private TextView textView ;
    private Button start, pause, reset, lap ;
    private long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    private Handler handler;
    private int Seconds, Minutes, MilliSeconds ;
    private ListView listView ;
    private String[] ListElements = new String[] {  };
    private List<String> ListElementsArrayList ;
    private ArrayAdapter<String> adapter ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_stopwatch, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.textView);

        start = view.findViewById(R.id.button);
        pause = view.findViewById(R.id.button2);
        reset = view.findViewById(R.id.button3);
        lap = view.findViewById(R.id.button4) ;
        listView = view.findViewById(R.id.listview1);

        handler = new Handler() ;

        ListElementsArrayList = new ArrayList<String>(Arrays.asList(ListElements));

        adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,
                ListElementsArrayList
        );

        listView.setAdapter(adapter);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);

                reset.setEnabled(false);

            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimeBuff += MillisecondTime;

                handler.removeCallbacks(runnable);

                reset.setEnabled(true);

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MillisecondTime = 0L ;
                StartTime = 0L ;
                TimeBuff = 0L ;
                UpdateTime = 0L ;
                Seconds = 0 ;
                Minutes = 0 ;
                MilliSeconds = 0 ;

                textView.setText("00:00:00");

                ListElementsArrayList.clear();

                adapter.notifyDataSetChanged();
            }
        });

        lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ListElementsArrayList.add(textView.getText().toString());

                adapter.notifyDataSetChanged();

            }
        });

    }

    public Runnable runnable = new Runnable() {
        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            textView.setText("" + Minutes + ":"
                             + String.format("%02d", Seconds) + ":"
                             + String.format("%03d", MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };
}
