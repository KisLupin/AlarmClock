package com.example.alarmclock.utils;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class AlarmSoundActive extends Service {

    private SoundAlert soundAlert = new SoundAlert();

    public AlarmSoundActive() {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("service is running....", "onBind");
        return soundAlert;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("service is running....", "ok");
        if (intent.getAction().equals("alarm")){
            soundAlert.setPath(Uri.parse("android.resource://com.example.alarmclock/raw/alert"));
            soundAlert.setContext(this);
            soundAlert.starMedia();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        soundAlert.stopAudio();
    }
}
