package com.example.genshin_damage_calc;

import android.content.Context;
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

public class setup_fragment extends Fragment implements AdapterView.OnItemSelectedListener{

    Spinner spinner,spinner1,spinner2,spinner3;
    ImageView imageView;
    String weapon_type,weapon_lvl="1/20";
    String weapon_name;
    updatefrag3 updatefrag3;
    String send_1;
    double send_2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_setups,container,false);
        if (getArguments()!=null)
            weapon_type = getArguments().getString("WEAPON_TYPE");
        else
            weapon_type = "bow";
        imageView = v.findViewById(R.id.imageView2);
        spinner1 = v.findViewById(R.id.spinner6);
        spinner = v.findViewById(R.id.spinner5);
        spinner2 = v.findViewById(R.id.spinner8);
        if (weapon_type.contains("bow")){
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(),R.array.weapon_bow, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
            spinner.setAdapter(adapter1);
            spinner.setOnItemSelectedListener(this);}
        else if (weapon_type.contains("sword"))
        {
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(),R.array.weapon_swords, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
            spinner.setAdapter(adapter1);
            spinner.setOnItemSelectedListener(this);
        }
        else if (weapon_type.contains("polearm")){
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(),R.array.weapon_polearms, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
            spinner.setAdapter(adapter1);
            spinner.setOnItemSelectedListener(this);
        }
        else if (weapon_type.contains("claymore")){
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(),R.array.weapon_claymores, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
            spinner.setAdapter(adapter1);
            spinner.setOnItemSelectedListener(this);
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.level, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(),R.array.refinement_rank, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(this);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.spinner5)
        {
           weapon_name = parent.getItemAtPosition(position).toString();
            switch (weapon_name)
            {
                case "Mistsplitter_Reforged":
                    imageView.setImageResource(R.mipmap.weapon_mistsplitter_reforged);
                    break;
                case "Haran_Geppaku_Futsu":
                    imageView.setImageResource(R.mipmap.weapon_haran_geppaku_futsu_2nd_3d);
                    break;
                case "Wolf\'s_Gravestone":
                    imageView.setImageResource(R.mipmap.wolf_a2);
                    break;
                case "Redhorn_Stonethresher":
                    imageView.setImageResource(R.mipmap.weapon_redhorn_stonethresher_2nd_3d);
                    break;
                case "Staff_Of_Homa":
                    imageView.setImageResource(R.mipmap.weapon_staff_of_homa_2nd_3d);
                    break;
                case "Primordial_Jade_Winged_Spear":
                    imageView.setImageResource(R.mipmap.weapon_primordial_jade_winged_spear_2nd_3d);
                    break;
                case "Aqua_Simulacra":
                    imageView.setImageResource(R.mipmap.weapon_aqua_simulacra_2nd_3d);
                    break;
                case "Polar_Star":
                    imageView.setImageResource(R.mipmap.weapon_polar_star_full_icon);
                    break;
                default:
                    imageView.setImageResource(R.mipmap.hu_tao_pfpf);
            }
        }
        else if (parent.getId() == R.id.spinner6)
        {
            weapon_lvl = parent.getItemAtPosition(position).toString();
        }
        try{
        DatabaseHelper databaseHelper = new DatabaseHelper(this.getContext());
        databaseHelper.get_weapon_stats(weapon_name,weapon_lvl);
        Toast.makeText(this.getContext(),databaseHelper.weapon_base_atk,Toast.LENGTH_SHORT).show();
        send_1 = databaseHelper.weapon_base_atk;
        send_2 = databaseHelper.second_stat_attr;
        }
        catch (Exception exception)
        {

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof updatefrag3)
            updatefrag3 = (updatefrag3) context;
        else {
            throw new RuntimeException(context.toString() + "implement updatefrag2");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        updatefrag3.update_stats(send_1,send_2);
    }
}
