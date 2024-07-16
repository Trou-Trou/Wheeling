package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;


public class HotelsActivity extends AppCompatActivity{
    ArrayList<Attributes> allAttributes = new ArrayList<>();

    ListView listview;

    CustomListAdapter adapter;

    private PreferencesManager prefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotels);
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
        Attributes stu1 = new Attributes(new GeoPoint( 37.442689, 24.945352), 1, "Hermes", 34, 34, "Ermoupoli", R.drawable.detailed_guid_button,"Hotel", "", 2, 2, 1, 1, 1, 1, 1, 1, " Ermis Hotel is the largest hotel in Ermoupolis and is housed in one of the historical neoclassical buildings of the island. It is in an extremely privileged position, on a narrow strip of land, on the right edge of the port, with the historic city center as a background. With a view of the deep blue waters of the Aegean on one side and the calm waters of the harbor on the other, the visitor is surrounded by sea.");
        allAttributes.add(stu1);
        allAttributes.add(new Attributes(new GeoPoint( 37.440308, 24.940385), 1, "Diogenis", 35, 35, "Ermoupoli", R.drawable.detailed_guid_button,"Hotel", "", 2, 2, 1, 1, 2, 2, 1, 1, "The A' category hotel Diogenis is a 19th century building, combining Neoclassical Architecture with modern technologies. In February 2015 it was completely renovated. Its 40 rooms are properly equipped to ensure the most comfortable stay possible for its customers, with modern decoration and tasteful earthy color combinations. It is the ideal accommodation proposal for those who desire quality, a family atmosphere and subtle luxury in the center of Ermoupolis."));
        allAttributes.add(new Attributes(new GeoPoint( 37.442540, 24.941870), 1, "Paladion", 36, 36, "Ermoupoli", R.drawable.detailed_guid_button,"Hotel", "", 2, 2, 1, 1, 2, 2, 1, 1, " If you want the ideal budget small hotel in Ermoupoli, you are in the right place. Welcome to Palladion Hotel, a place made for travelers like you. Palladion Hotel is a budget friendly small hotel offering flat screen TV and air conditioning in the rooms. A favorite traveler activity is nearby (National Resistance Monument (0.1 km)), making Palladion Hotel a good choice for those staying in Hermoupoli. "));
        allAttributes.add(new Attributes(new GeoPoint( 37.444908, 24.944995), 1, "Ploes", 43, 43, "Ermoupoli", R.drawable.detailed_guid_button,"Hotel", "", 2, 2, 1, 1, 2, 2, 1, 1, "Every traveler needs the right hotel to recharge. For visitors to Ermoupoli, Hotel Ploes is an excellent choice for rest and rejuvenation. Well-known for its family-friendly environment and proximity to great restaurants and attractions, Hotel Ploes makes it easy for those staying here to discover some of the best of Ermoupolis. "));
        allAttributes.add(new Attributes(new GeoPoint(37.440132, 24.940342), 1, "Esperance 1", 37, 37, "Ermoupoli", R.drawable.detailed_guid_button,"Hotel", "", 2, 2, 1, 1, 2, 2, 1, 1, "The lovely decor in pastel tones will help you relax in one of our fully renovated rooms and enjoy the sea view from your window or balcony! Start your day enjoying your coffee in our large outdoor patio or your breakfast from the buffet in the breakfast area. Esperance 1 is located on the main coastal road of Ermoupoli in the port of Syros, near the Neorion Shipyard (300 meters). We will be more than happy to serve you and help you have a pleasant stay."));


    }
}

