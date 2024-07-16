package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

public class ShopsActivity extends AppCompatActivity {

    ArrayList<Attributes> allAttributes = new ArrayList<>();

    ListView listview;

    CustomListAdapter adapter;

    private PreferencesManager prefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shops);
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
        allAttributes.add(new Attributes(new GeoPoint( 37.44353613122226, 24.94203488231233), 1, "Gas Mask", 38, 38, "Ermoupoli",1,"Clothing", "Fashion store", 1, 1, 2, 2, 2, 2, 1, 1, ""));
        allAttributes.add(new Attributes(new GeoPoint( 37.444027836423096, 24.943429386382075), 1, "Hondos Center", 39, 39, "Ermoupoli", 1,"Cosmetics", "Shop", 1, 1, 2, 2, 2, 2, 1, 1, ""));
        allAttributes.add(new Attributes(new GeoPoint( 37.445071701204185, 24.94166923591363), 1, "Themelio", 40, 40, "Ermoupoli",1,"Arts ", "Crafts", 1, 1, 2, 2, 2, 2, 1, 1, "Themelioshop.gr is a model online store, which with more than 20 years of experience, we address individuals as well as professionals and we base our operation on completely personalized online services to our customers."));
        allAttributes.add(new Attributes(new GeoPoint( 37.443865062268344, 24.944396734309382), 1, "La Maison", 41, 41, "Ermoupoli", 1,"Household", "supplies", 1, 1, 2, 2, 2, 2, 1, 1, ""));
        allAttributes.add(new Attributes(new GeoPoint( 37.444653,24.943681), 1, "Franca", 42, 42, "Ermoupoli", 1,"Hair", "Salon", 1, 1, 2, 2, 2, 2, 1, 1, ""));



    }
}