package com.example.genshin_damage_calc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
public class MainActivity extends AppCompatActivity implements updatefrag,updatefrag2,updatefrag3{
    Fragment selected_fragment;
    BottomNavigationView bottomNavigationView;
    Bundle one_fragment,two_fragment,three_fragment,four_fragment,five_fragment;
    protected static String The_weapon;
    public static String update_atk;
    protected static String a1,a2,a3;
    private  String keep_track;
    BroadcastReceiver receiver;


    public MainActivity()
    {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        selected_fragment = new target_fragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selected_fragment).commit();
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(navlistr);
        configureReceiver();
    }
    private  NavigationBarView.OnItemSelectedListener navlistr = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case  R.id.nav_characters:
                    selected_fragment =  new characters_fragment();
                    keep_track = "characters";
                    break;
                case  R.id.nav_target:
                    selected_fragment = new target_fragment();
                    keep_track="target";
                    break;
                case  R.id.nav_setup:
                    selected_fragment = new setup_fragment();
                    keep_track = "setup";
                    break;
                case  R.id.nav_damage:
                    selected_fragment = new damage_fragment();
                    keep_track = "damage";
                    break;
                case  R.id.nav_HowToUse:
                    selected_fragment = new howtouse_fragment();
                    keep_track = "howtouse";
                    break;
            }
            if (keep_track.contains("setup"))
            {
                selected_fragment.setArguments(three_fragment);
            }
            else if (keep_track.contains("characters"))
            {
                selected_fragment.setArguments(one_fragment);
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selected_fragment).commit();
            return  true;
        }
    };
    private void configureReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.genshin_damage_calc");
        receiver = new Myreceiver();
        registerReceiver(receiver, filter);
    }

    public void  c(String s)
    {
        The_weapon = s;
        three_fragment = new Bundle();
        three_fragment.putString("WEAPON_TYPE",The_weapon);
    }
    public void c1(String s1,String s2,String s3)
    {
        a1 = s1;
        a2 = s2;
        a3 = s3;
        one_fragment = new Bundle();
        one_fragment.putString("CHARA_NAME",a1);
        one_fragment.putString("CHARA_LVL",a2);
        one_fragment.putString("CHARA_STATS",a3);
    }
    public void c2(String s1,double d1)
    {
        update_atk = s1;
    }
    @Override
    public void update(String s) {
        c(s);
    }
    @Override
    public void update_characters(String s1,String s2,String s3) {
        c1(s1,s2,s3);
    }

    @Override
    public void update_stats(String s1, double d1) {
        c2(s1,d1);
    }
}