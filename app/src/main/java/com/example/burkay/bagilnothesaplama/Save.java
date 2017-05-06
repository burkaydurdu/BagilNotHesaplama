package com.example.burkay.bagilnothesaplama;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Save {

    private static final String PREF_NAME = "Android_Note";
    private static final String NOTE = "Note";
    private  SharedPreferences preferences;
    private  Editor  editor;
    private  Context context;

    public Save(Context context){
        this.context = context;
        preferences =  (SharedPreferences) context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void setNote(int note){
        editor.putInt(NOTE,note);
        editor.commit();
    }
    public int getNote(){

        return preferences.getInt(NOTE, 45);
    }
}
