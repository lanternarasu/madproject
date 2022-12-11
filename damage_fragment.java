package com.example.genshin_damage_calc;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class damage_fragment extends Fragment {
    AnimationDrawable wifiAnimation;
    View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =  inflater.inflate(R.layout.fragment_damage,container,false);
        ImageView imageView = v.findViewById(R.id.image);
        imageView.setBackgroundResource(R.drawable.animation);
        imageView.post(new Runnable() {
            @Override
            public void run() {
                wifiAnimation = (AnimationDrawable) imageView.getBackground();
                wifiAnimation.start();
            }
        });

        return  v;
    }


}
