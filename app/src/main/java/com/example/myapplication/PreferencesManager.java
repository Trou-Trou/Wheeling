package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferencesManager {

    private static PreferencesManager _instance;

    private Context context;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor sharedPrefEditor;

    private PreferencesManager() {

    }

    public static PreferencesManager instance (Context context) {
        if (_instance == null) {
            _instance = new PreferencesManager();
            _instance.configSessionUtils(context.getApplicationContext());
        }
        return _instance;
    }

    private static PreferencesManager instance() {
        return _instance;
    }

    private void configSessionUtils(Context context) {
        this.context = context;
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPrefEditor = sharedPref.edit();
    }

    public void storeValueString(String key, String value) {
        sharedPrefEditor.putString(key, value);
        sharedPrefEditor.commit();
    }

    public String fetchValueString(String key) {
        return sharedPref.getString(key, null);
    }

    public void deleteAllPreferences() {
        sharedPrefEditor.clear();
        sharedPrefEditor.apply();
    }
}


