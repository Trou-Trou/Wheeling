package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;


public class MuseumActivity extends AppCompatActivity{
    ArrayList<Attributes> allAttributes = new ArrayList<>();

    ListView listview;

    CustomListAdapter adapter;

    private PreferencesManager prefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum);
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
        Attributes stu1 = new Attributes(new GeoPoint(37.447083,24.943806), 1,"Kyveli Institute", 29, 29, "Ermoupoli", 1,"Public Space", "Museum", 1, 1, 2, 2, 2, 2, 1, 1, "The Kyveli Institute is a legal entity under private law of a non-profit nature. It was founded in 1999 with the aim of rescuing, researching, exploiting and highlighting historical material related to the Greek and European theater of the 20th century, through the life and work of the great actress Kyveli and her family.");
        allAttributes.add(stu1);
        allAttributes.add(new Attributes(new GeoPoint(37.438023,24.934601), 1, "Industrial Museum", 28, 28, "Ermoupoli", 1,"Public Space", "Museum", 1, 1, 2, 2, 2, 2, 1, 1, "The Industrial Museum of Ermoupolis was founded in 2000 by the Center for Technical Culture and is housed in 4 industrial buildings of the 19th century, the Kornilaki Tannery, the Anairousi Leatherworks, the Velissaropoulou Textiles and the Katsimantis Dyeing Factory opposite the Syros General Hospital. The museum houses a collection of tools, equipment and machinery of the golden industrial age from workshops of confectionery, textile, tannery, printing, mechanical engineering, glassmaking, as well as a digital image and sound archive with workers' testimonies, maps and plans, a 3D visualization of industrial buildings and finds from the wreck of the steamer Patris."));
        allAttributes.add(new Attributes(new GeoPoint(37.445167,24.942292), 1, "Archaeological Museum", 30, 30, "Ermoupoli",1,"Public Space", "Museum", 1, 1, 2, 2, 2, 2, 1, 1, "The Archaeological Museum of Syros was founded in 1834 and is one of the oldest museums in Greece. Since 1899, it has been housed in 4 rooms of the Ermoupolis Town Hall and offers an independent entrance from the historical Miaouli Square. The museum's collections include exhibits such as the Depas Amphikypellon, a 15 cm high tubular cup from the late 3rd millennium, Pyxida, a 7 cm high angel from the second half of the 3rd millennium, the marble figurine of a woman  from 730 BC, as well as other Proto-Cycladic and Byzantine exhibits, sculptures and inscriptions. There is also an exhibition of archaeologist Christos Tsoundas in the museum."));

    }
}

