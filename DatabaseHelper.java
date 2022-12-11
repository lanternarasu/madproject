package com.example.genshin_damage_calc;

import android.content.ContentValues;
import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.IOException;

public class DatabaseHelper extends SQLiteAssetHelper {
    public static final CharSequence COLUMN_ID = "id";
    protected int HP,ATK,DEF,Elemental_Mastery,Healing_Bonus,Shield_Strength;
    protected double CRIT_Rate,CRIT_DMG,Energy_Recharge;
    protected double Pyro_DMG_Bonus,Hydro_DMG_Bonus,Dendro_DMG_Bonus,Electro_DMG_Bonus,Anemo_DMG_Bonus,Cryo_DMG_Bonus,Geo_DMG_Bonus,Physical_DMG_Bonus,second_stat_attr;
    protected int Normal_ATK_Speed,Charged_ATK_Speed;
    protected String weapon_type,weapon_base_atk,second_stat_name;
    protected static String TABLE = "character_stats";
    public DatabaseHelper(Context context) {
        super(context, "genshin_impact.db", null, 1);
    }

    public void get_character_attributes(String text_character_name,String text_character_lvl)
    {
        String queryString = "Select * from  character_stats where character_name =" + "\""+text_character_name+"\"" + " and character_lvl=" + "\""+text_character_lvl+"\"";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(queryString,null);
        if (cursor.moveToFirst())
        {
            do {
                HP = cursor.getInt(3);
                ATK = cursor.getInt(4);
                DEF = cursor.getInt(5);
                Elemental_Mastery = cursor.getInt(6);
                CRIT_Rate = cursor.getDouble(7);
                CRIT_DMG = cursor.getDouble(8);
                Healing_Bonus = cursor.getInt(9);
                Energy_Recharge = cursor.getDouble(10);
                Shield_Strength = cursor.getInt(11);
                Pyro_DMG_Bonus = cursor.getDouble(12);
                Hydro_DMG_Bonus = cursor.getDouble(13);
                Dendro_DMG_Bonus = cursor.getDouble(14);
                Electro_DMG_Bonus = cursor.getDouble(15);
                Anemo_DMG_Bonus = cursor.getDouble(16);
                Cryo_DMG_Bonus = cursor.getDouble(17);
                Geo_DMG_Bonus = cursor.getDouble(18);
                Physical_DMG_Bonus = cursor.getDouble(19);
                Normal_ATK_Speed = cursor.getInt(20);
                Charged_ATK_Speed = cursor.getInt(21);
                weapon_type = cursor.getString(22);

            }while (cursor.moveToNext());
        }
        else {
            // TASK FAILED
        }
        cursor.close();
        sqLiteDatabase.close();
    }
    public  void get_weapon_stats(String weapon_NAME, String weapon_LVL)
    {
        String query = "Select * from weapons where weapon_lvl = " + "\""+weapon_LVL+"\"" + "and weapon_name = " + "\""+weapon_NAME+"\"" ;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);
        if (cursor.moveToFirst())
        {
            do {
                    weapon_base_atk = cursor.getString(4);
                    second_stat_name = cursor.getString(5);
                    second_stat_attr = cursor.getDouble(6);
            }while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
    }
}

