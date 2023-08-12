package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class CreateService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("CreateService", "onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("CreateService", "onBind");
        CreateNotify createNotify = new CreateNotify();
        String title = "Thêm thành công";
        String content = "Đã thêm thành công";
        int id = 1;
        createNotify.postNotification(getApplicationContext(), title, content, id);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d("CreateService", "onDestroy");
        super.onDestroy();
    }
}
