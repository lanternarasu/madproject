package com.example.genshin_damage_calc;

import static java.lang.Integer.parseInt;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.widget.TextView;

public class MyService extends Service {
    Context context1;
    private static  int val;
    public MyService() {

    }
    public  MyService(int x)
    {
        val = x;
    }

    @Override
    public void onCreate() {

        super.onCreate();

        }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try{
        int output = val + Integer.parseInt(MainActivity.update_atk);
        characters_fragment_attributes.atk_stat.setText(Integer.toString(output));}
        catch (NumberFormatException exception)
        {

        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSelf();
    }
    public void some(int val) {

    }
}