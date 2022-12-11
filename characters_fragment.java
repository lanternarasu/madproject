package com.example.genshin_damage_calc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class characters_fragment extends Fragment implements AdapterView.OnItemSelectedListener{
    ImageView imageView;
    updatefrag2 u1;
    String text_character_name,text_character_lvl,text_main;
    protected String keep_track_name,keep_track_lvl,keep_track_stats;
    Spinner spinner,spinner1,spinner2;
    ArrayAdapter<CharSequence> adapter,adapter1,adapter2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_characters,container,false);

        try {
            imageView = v.findViewById(R.id.imageView2);
            spinner = v.findViewById(R.id.spinner);
            adapter = ArrayAdapter.createFromResource(getActivity(),R.array.attributes, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
            spinner1 = v.findViewById(R.id.spinner2);
            adapter1 = ArrayAdapter.createFromResource(getActivity(),R.array.characters_pfp, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
            spinner1.setAdapter(adapter1);
            spinner1.setOnItemSelectedListener(this);
            spinner2 = v.findViewById(R.id.spinner3);
            adapter2 = ArrayAdapter.createFromResource(getActivity(),R.array.level, android.R.layout.simple_spinner_item);
            adapter2.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
            spinner2.setAdapter(adapter2);
            spinner2.setOnItemSelectedListener(this);
        }
        catch (NullPointerException exception){
            Toast.makeText(getActivity(),"error happening",Toast.LENGTH_SHORT).show();
        }
        if (getArguments()!=null)
        {
            keep_track_name = getArguments().getString("CHARA_NAME");
            keep_track_lvl = getArguments().getString("CHARA_LVL");
            keep_track_stats = getArguments().getString("CHARA_STATS");
            int p1 = adapter1.getPosition(keep_track_name);
            spinner1.setSelection(p1);
            int p2 = adapter.getPosition(keep_track_stats);
            spinner.setSelection(p2);
            int p3 = adapter2.getPosition(keep_track_lvl);
            spinner2.setSelection(p3);
        }
        return  v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof updatefrag2)
            u1 = (updatefrag2) context;
        else {
            throw new RuntimeException(context.toString() + "implement updatefrag2");
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.spinner){
         text_main = adapterView.getItemAtPosition(i).toString();
        Fragment selectedFragment = null;
        switch (text_main){
            case "Attributes":
                selectedFragment = new characters_fragment_attributes(text_character_name,text_character_lvl);

                break;
            case  "Artifacts":
                selectedFragment = new characters_fragment_artifacts();

                break;

        }
        try {
            if (text_main.contains("Attributes"))
            {
                getChildFragmentManager().beginTransaction().replace(R.id.fragment_container_characters,selectedFragment,"Attributes_fragment").commit();

            }
            getChildFragmentManager().beginTransaction().replace(R.id.fragment_container_characters,selectedFragment).commit();
        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(),"IN DEVELOPMENT",Toast.LENGTH_SHORT).show();
        }
        }
        else if (adapterView.getId() == R.id.spinner2){
            text_character_name = adapterView.getItemAtPosition(i).toString();
            switch (text_character_name){
                case "Amber":
                    imageView.setImageResource(R.mipmap.amber_pfp);

                    break;
                case "Ayaka":
                    imageView.setImageResource(R.mipmap.ayaka_pfp);

                    break;
                case "Ayato":
                    imageView.setImageResource(R.mipmap.ayato_pfp);
                    break;
                case "Hu Tao":
                    imageView.setImageResource(R.mipmap.hu_tao_pfpf);
                    break;
                case "Itto":
                    imageView.setImageResource(R.mipmap.arataki_itto_pfp);
                    break;
                case "Keqing":
                    imageView.setImageResource(R.mipmap.keqing_pfp);
                    break;
                default:
                    imageView.setImageResource(R.mipmap.amber_pfp);
                    break;
            }
            if (text_main.contains("Attributes")){
            Fragment fragment = new characters_fragment_attributes(text_character_name,text_character_lvl);
            try {
                getChildFragmentManager().beginTransaction().replace(R.id.fragment_container_characters,fragment).commit();
            }
            catch (Exception e)
            {
                Toast.makeText(getActivity(),"PROBLEM AT CHARACTER IMAGE ATTRIBTE",Toast.LENGTH_SHORT).show();
            }}
        }
        else if (adapterView.getId() == R.id.spinner3)
        {
            text_character_lvl = adapterView.getItemAtPosition(i).toString();

            if (text_main.contains("Attributes")){
            Fragment fragment = new characters_fragment_attributes(text_character_name,text_character_lvl);
            try {
                getChildFragmentManager().beginTransaction().replace(R.id.fragment_container_characters,fragment).commit();
            }
            catch (Exception e)
            {
                Toast.makeText(getActivity(),"PROBLEM AT CHARACTER LEVEL ATTRIBTE",Toast.LENGTH_SHORT).show();
            }}
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onStop() {
        u1.update_characters(text_character_name,text_character_lvl,text_main);
        super.onStop();
    }

}
