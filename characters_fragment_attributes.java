package com.example.genshin_damage_calc;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class characters_fragment_attributes extends Fragment implements updatefrag4 {
    updatefrag u;
    private String text_character_name,text_character_lvl,weapon_type;
    protected DatabaseHelper databaseHelper;
    TextView hp_stat;
    static TextView atk_stat;
    TextView def_stat;
    TextView em_stat;
    TextView CR_stat;
    TextView CD_stat;
    TextView hb_stat;
    TextView er_stat;
    TextView ss_stat;
    TextView pyro_stat;
    TextView hydro_stat;
    TextView dendro_stat;
    TextView electro_stat;
    TextView anemo_stat;
    TextView cryo_stat;
    TextView geo_stat;
    TextView physical_stat;
    TextView na_stat;
    TextView ca_stat;
    public characters_fragment_attributes(String text_character_name,String text_character_lvl) {
        this.text_character_name = text_character_name;
        this.text_character_lvl=text_character_lvl;
    }
    public characters_fragment_attributes()
    {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_characters_attributes,container,false);
        hp_stat = v.findViewById(R.id.hp_stat);
        atk_stat = v.findViewById(R.id.atk_stat);
        def_stat = v.findViewById(R.id.def_stat);
        em_stat = v.findViewById(R.id.em_stat);
        CR_stat = v.findViewById(R.id.CR_stat);
        CD_stat = v.findViewById(R.id.CD_stat);
        hb_stat = v.findViewById(R.id.HB_stat);
        er_stat = v.findViewById(R.id.ER_stat);
        ss_stat = v.findViewById(R.id.SS_stat);
        pyro_stat = v.findViewById(R.id.pyro_stat);
        hydro_stat = v.findViewById(R.id.hydro_stat);
        dendro_stat = v.findViewById(R.id.dendro_stat);
        electro_stat = v.findViewById(R.id.electro_stat);
        anemo_stat = v.findViewById(R.id.anemo_stat);
        cryo_stat = v.findViewById(R.id.cryo_stat);
        geo_stat = v.findViewById(R.id.geo_stat);
        physical_stat = v.findViewById(R.id.physical_stat);
        na_stat = v.findViewById(R.id.na_stat);
        ca_stat = v.findViewById(R.id.ca_stat);
        databaseHelper = new  DatabaseHelper(this.getContext());
        databaseHelper.get_character_attributes(text_character_name,text_character_lvl);
        Helping_func(text_character_name,text_character_lvl);
        return v;
    }
    public void Helping_func(String text1,String text2)
    {
        hp_stat.setText(Integer.toString(databaseHelper.HP));
        atk_stat.setText(Integer.toString(databaseHelper.ATK));
        try {
            MyService myService = new MyService(databaseHelper.ATK);
            Intent intent = new Intent(getActivity(),MyService.class);
            getActivity().startService(intent);
          //  MyService myService = new MyService(getActivity());
          //  myService.some(databaseHelper.ATK);
        }
        catch (Exception e)
        {

        }
        def_stat.setText(Integer.toString(databaseHelper.DEF));
        em_stat.setText(Integer.toString(databaseHelper.Elemental_Mastery));
        CR_stat.setText(Double.toString(databaseHelper.CRIT_Rate));
        CD_stat.setText(Double.toString(databaseHelper.CRIT_DMG));
        hb_stat.setText(Integer.toString(databaseHelper.Healing_Bonus));
        ss_stat.setText(Integer.toString(databaseHelper.Shield_Strength));
        er_stat.setText(Double.toString(databaseHelper.Energy_Recharge));
        pyro_stat.setText(Double.toString(databaseHelper.Pyro_DMG_Bonus));
        hydro_stat.setText(Double.toString(databaseHelper.Hydro_DMG_Bonus));
        dendro_stat.setText(Double.toString(databaseHelper.Dendro_DMG_Bonus));
        electro_stat.setText(Double.toString(databaseHelper.Electro_DMG_Bonus));
        anemo_stat.setText(Double.toString(databaseHelper.Anemo_DMG_Bonus));
        cryo_stat.setText(Double.toString(databaseHelper.Cryo_DMG_Bonus));
        geo_stat.setText(Double.toString(databaseHelper.Geo_DMG_Bonus));
        physical_stat.setText(Double.toString(databaseHelper.Physical_DMG_Bonus));
        na_stat.setText(Integer.toString(databaseHelper.Normal_ATK_Speed));
        ca_stat.setText(Integer.toString(databaseHelper.Charged_ATK_Speed));
        weapon_type = databaseHelper.weapon_type;
        u.update(weapon_type);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof updatefrag)
            u = (updatefrag)context;
        else
            throw new RuntimeException(context.toString() + "implement updatefrag");
    }

    @Override
    public void one(String s) {
        String s1 = atk_stat.getText().toString();
        s1 = Integer.toString(Integer.parseInt(s1) + Integer.parseInt(s));
        atk_stat.setText(s1);
    }
}
