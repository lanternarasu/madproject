package com.example.genshin_damage_calc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class target_fragment extends Fragment implements AdapterView.OnItemSelectedListener {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_target,container,false);
        try {
            Spinner spinner = v.findViewById(R.id.spinner4);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.target, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(),"spinner not shown",Toast.LENGTH_SHORT).show();
        }
        return  v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Fragment selectedFragment = null;
        switch (text){
            case "Target":
                selectedFragment = new target_fragment_target();
                break;
        }
        try {
            getChildFragmentManager().beginTransaction().replace(R.id.fragment_container_target, selectedFragment).commit();
        }
        catch (Exception e)
        {
            Toast.makeText(getActivity(), "In development", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
