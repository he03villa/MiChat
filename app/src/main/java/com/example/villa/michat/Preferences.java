package com.example.villa.michat;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Villa on 19/12/2017.
 */

public class Preferences {

    public static final String STRING_PREFEREN ="villa.michat.Mesajes.Mensajeri";
    public static final String STRING_ESTADO = "estado.button.secion";
    public static final String PREFERENCE_USUARIO = "usuario.login";

    public static void saveBoolean(Context c, boolean b,String key){
        SharedPreferences preferences = c.getSharedPreferences(STRING_PREFEREN,c.MODE_PRIVATE);
        preferences.edit().putBoolean(key,b).apply();
    }

    public static void saveString(Context c, String  b,String key){
        SharedPreferences preferences = c.getSharedPreferences(STRING_PREFEREN,c.MODE_PRIVATE);
        preferences.edit().putString(key,b).apply();
    }

    public static boolean getBoolean(Context c,String key){
        SharedPreferences preferences = c.getSharedPreferences(STRING_PREFEREN,c.MODE_PRIVATE);
        return preferences.getBoolean(key,false);//Si es que nunca se a guardado nada en esta key pues retornara falso
    }

    public static String getString(Context c,String key){
        SharedPreferences preferences = c.getSharedPreferences(STRING_PREFEREN,c.MODE_PRIVATE);
        return preferences.getString(key,"");//Si es que nunca se a guardado nada en esta key pues retornara una cadena vacia
    }
}
