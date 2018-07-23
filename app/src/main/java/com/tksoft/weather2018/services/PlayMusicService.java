package com.tksoft.weather2018.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.tksoft.weather2018.R;

public class PlayMusicService extends Service {
    MediaPlayer player;
    public PlayMusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(getApplicationContext(), R.raw.t);
        Log.d("TestService", "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TestService", "onStartCommand: ");
        player.start();
        Toast.makeText(this, "Nhạc đang chạy", Toast.LENGTH_SHORT).show();
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        Log.d("TestService", "onDestroy: ");
        player.release();
        Toast.makeText(this, "Dừng lại", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }
}
