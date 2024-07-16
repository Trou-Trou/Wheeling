package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;


public class AttractionsActivity extends AppCompatActivity{
    ArrayList<Attributes> allAttributes = new ArrayList<>();

    ListView listview;

    CustomListAdapter adapter;

    private PreferencesManager prefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attractions);
        getSupportActionBar().hide();

        initializeViews();
        initializePreferences();

        initializeData();

        adapter = new CustomListAdapter(this, R.layout.listview_row, allAttributes);

        listview.setAdapter(adapter);

    }

    private void initializeViews() {
        listview = findViewById(R.id.listview);
    }

    private void initializePreferences() {
        prefManager = PreferencesManager.instance(this.getApplicationContext());

    }

    private void initializeData() {
        createHardcoded();
    }

    private void createHardcoded() {


    }
}

