package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;


public class CoffeeActivity extends AppCompatActivity{
    ArrayList<Attributes> allAttributes = new ArrayList<>();

    ListView listview;

    CustomListAdapter adapter;

    private PreferencesManager prefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
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

        Attributes stu1 = new Attributes(new GeoPoint( 37.444369,24.942912), 1, "A club", 1, 1, "Ermoupoli", 1,"Cafe", "Bar", 1, 1, 2, 2, 2, 2, 1, 1, "Cafe near the Panagouli park. You can watch weakly football and basketball matches.");
        allAttributes.add(stu1);
        allAttributes.add(new Attributes(new GeoPoint( 37.444653,24.943681), 1, "Everest", 2, 2, "Ermoupoli", 1,"Fast Food", "Cafe", 1, 1, 2, 2, 2, 2, 1, 1, "Includes: Fast food, Coffee Special diets Suitable for vegetarians, vegan options Meals Breakfast, Lunch, Brunch"));
        allAttributes.add(new Attributes(new GeoPoint( 37.443417,24.944678), 1, "Podilato",3, 3, "Ermoupoli", 1,"Cafe", "Cocktail-Bar", 1, 1, 2, 2, 1,1,1,1, "PRICE RANGE €7 - €15 Includes: Coffee, European, Hygiene Special diets Suitable for vegetarians."));
        allAttributes.add(new Attributes(new GeoPoint(37.444733,24.943964), 1, "Kouchico", 4, 4, "Ermoupoli", 1,"Cafe", "Bar", 1,1, 2, 2, 2, 2, 1, 1, "PRICE RANGE €4 - €15 Meals Drinks POSSIBILITIES Outdoor Seating, Seating, Wheelchair Accessible, Serves Alcohol, Full Bar, Accepts American Express, Accepts Mastercard, Accepts Visa, Digital Payments, Free WiFi, Accepts Discover, Accepts Credit Cards, Table Service, Wine and Beer, Dog Friendly."));
        allAttributes.add(new Attributes(new GeoPoint( 37.443771,24.944354), 1, "Bloom", 5, 5, "Ermoupoli", 1,"Cafe", "Bar", 1, 1, 2, 2, 2, 2, 1, 1, "KITCHEN Coffee Meals Breakfast, Lunch, Dinner, Brunch, Drinks POSSIBILITIES Packed food."));
        allAttributes.add(new Attributes(new GeoPoint(37.443530,24.943790), 1, "Plaza", 6, 6, "Ermoupoli", 1,"Cafe", "Breakfast", 1, 1, 2, 2, 2, 2,1, 1, "PRICE RANGE €7 - €15 Includes: Coffee, European, Hygiene Special diets Suitable for vegetarians"));
        allAttributes.add(new Attributes(new GeoPoint( 37.443093,24.944452), 1, "Okio", 7, 7, "Ermoupoli",1,"Cafe", "Bar", 1, 1, 2, 2, 2, 2, 1,1, "Includes: Bar, Cafe, Greek Special diets Suitable for vegetarians, vegan options Meals Breakfast, Brunch, Lunch, Drinks."));
        allAttributes.add(new Attributes(new GeoPoint( 37.442951, 24.944668), 1, "Chalandriani", 8,8, "Ermoupoli", 1,"Cafe", "Bar", 1,1, 2, 2, 2, 2, 1, 1, "PRICE RANGE €3 - €20 KITCHEN Mediterranean, Greek Meals Breakfast, Lunch, Dinner."));
        allAttributes.add(new Attributes(new GeoPoint( 37.442841, 24.944813), 1, "Boheme del mar", 9, 9, "Ermoupoli", 1,"Cafe", "Bar", 1, 1, 2, 2, 2, 2, 2, 2, "KITCHEN Greek Meals Drinks POSSIBILITIES Serves Alcohol, Waitstaff, Seating."));
        allAttributes.add(new Attributes(new GeoPoint(37.444730,24.941987), 1, "Barrio", 10, 10, "Ermoupoli", 1,"Cafe", "Bar", 1, 1 , 2, 2, 2, 2, 1, 1, "Includes: Bars, Cafes, Pubs, Diners, Bars that serve food Meals Lunch, Dinner, Brunch, Late Night, Drinks POSSIBILITIES Reservations, Outdoor Seating, Seating, Serves Alcohol, Full Bar, Wine and Beer, Digital Payments, Free Wifi, Accepts Credit Cards, Table Service"));
        allAttributes.add(new Attributes(new GeoPoint(37.443946,24.943042), 1, "Coffee island", 11, 11, "Ermoupoli", 1,"Cafe", "Take Away", 1, 1, 2, 2, 2, 2, 1, 1, "At Coffee Island, of course, you can find the best hot or cold coffee from selected varieties, with excellent cream and now also in vegan options. Yes, you will find both the Greek and the classic frappe! And if you want to try something different, you also have many options for refreshing tea such as lime, strawberry or hibiscus! And because the innovation at Coffee Island does not stop at coffee, you will also find Milkshakes with the flavor of mastic and pink pepper. What do you say, will you try it? The options we recommend from Coffee Island (Syros, Hermoupoli) are: Cappuccino, Espresso Freddo 12oz, Iced Latte 12oz."));
        allAttributes.add(new Attributes(new GeoPoint(37.444384,24.943940), 1, "Jar", 12, 12, "Ermoupoli", 1,"Cafe", "Breakfast", 1, 1, 2, 2, 2, 2, 1, 1, "Includes: Bar, Cafe,European, Greek Special diets Suitable for vegetarians, vegan options Meals Breakfast, Brunch, Drinks"));
        allAttributes.add(new Attributes(new GeoPoint(37.444119, 24.944208), 1, "Bros Wap", 13, 13, "Ermoupoli", 1,"Sandwich Shop", "Cafe", 1, 1, 2, 2, 2, 2, 1, 1, "Description: Includes: Coffee, Greek."));
        allAttributes.add(new Attributes(new GeoPoint( 37.440888,24.940725), 1, "Gregory’s", 14, 14, "Ermoupoli", 1,"Cafe", "Sandwich Shop", 1, 1, 2, 2, 3, 3, 1, 1, "KITCHEN Coffee Special diets Suitable for vegetarians Meals Breakfast."));
        allAttributes.add(new Attributes(new GeoPoint(37.441101,24.940943), 1, "Mikel", 15, 15, "Ermoupoli", 1,"Cafe", "Take Away", 1, 1, 2, 2, 3, 3, 1, 1, "Mikel Coffee Company is not only coffee, environment and service, it is a part that has become a daily routine for thousands of customers."));
        allAttributes.add(new Attributes(new GeoPoint( 37.443867,24.944054), 1, "Megaron", 16, 16, "Ermoupoli", 1,"Cafe", " ", 3, 3,3, 3, 3, 3, 3, 3, "KITCHEN Coffee"));
        allAttributes.add(new Attributes(new GeoPoint(37.446742,24.947646), 1, "Ciel", 17, 17, "Ermoupoli", 1,"Beach-Cafe", "Beach-Bar",2, 2, 2, 2, 1, 1, 2, 2, "Includes: Bar, Cafe, Mediterranean, European, Greek, Seafood Special diets Vegan options Meals Breakfast, Lunch, Dinner"));

    }
}


