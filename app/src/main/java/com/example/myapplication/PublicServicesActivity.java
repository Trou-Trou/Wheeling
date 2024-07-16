package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

public class PublicServicesActivity extends AppCompatActivity {

    ArrayList<Attributes> allAttributes = new ArrayList<>();

    ListView listview;

    CustomListAdapter adapter;

    private PreferencesManager prefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_services);
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

        Attributes stu1 = new Attributes(new GeoPoint(37.445507,24.943623), 1, "Theatre Apollon", 31, 31, "Ermoupoli", 1,"Public Building", "Theatre", 2, 2, 2, 2, 2, 2, 1, 1, "The Municipal Theater of Apollo is an emblem of Ermoupolis and is inextricably linked with its history. The city, a creation of refugees at the beginning of the 19th century, was a social, economic and architectural miracle, with an intellectual movement and radiation surprising for the time.");
        allAttributes.add(stu1);
        allAttributes.add(new Attributes(new GeoPoint(37.445064,24.942736), 1, "Ermoupolis Town Hall", 32, 32, "Ermoupoli", 1,"Town Hall", " ", 2, 2, 2, 2, 2, 1, 1, 1,"The Town Hall of Ermoupoli, which adorns Miaouli Square, is one of the largest and most imposing town halls in Greece. It was built in 1876 under the supervision of Ernesto Ziller and its cost is estimated at around 1,300,000 drachmas, an enormous amount for the time! It is a very special building with a three-story view from the square side and two-story from the back side which has impressive details, two wings with five vertical windows on the right and left, ledges, towers and in general a characteristic architecture inspired by 3 different rhythms."));



    }
}