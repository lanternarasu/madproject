package com.example.genshin_damage_calc;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Myreceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,"fill up necessary stuff in character attributes -> weapons -> target -> damage checker",Toast.LENGTH_SHORT).show();
    }
}
