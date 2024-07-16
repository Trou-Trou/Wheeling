package com.example.myapplication;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.util.GeoPoint;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {
        ArrayList<Attributes> allAttributes = new ArrayList<>();

        ListView listview;

        CustomListAdapter adapter;

        private PreferencesManager prefManager;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_food);
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

            Attributes stu1 = new Attributes(new GeoPoint( 37.444435,24.943285), 1, "Glykotexneio", 18, 18, "Ermoupoli",1,"Candy Store", "Cafe", 1, 1, 2, 2, 2, 2 ,1, 1 , "PRICE RANGE €3 - €15 KITCHEN Greek");
            allAttributes.add(stu1);
            allAttributes.add(new Attributes(new GeoPoint( 37.444216,24.943077), 1, "Apergis Candy Store", 19, 19, "Ermoypoli", 1,"Candy store", " ", 1, 2, 2, 2, 2 ,1, 1 , 1,"PRICE RANGE €5 - €10 INCLUDES: Greek, Coffee Special diets Suitable for vegetarians, vegan options"));
            allAttributes.add(new Attributes(new GeoPoint( 37.443464,24.943145), 1, "Leivadaras", 20, 20, "Ermoupoli", 1,"Loykoymia", " ",1, 1, 2, 2, 2, 2 ,1, 1 , "The Livadara family started in 1923 manufacturing Turkish delights, halvad pies, halva and other sugary products. In 1933 at the Thessaloniki Exhibition and in 1936 at the Pan-Cycladic Exhibition of Traditional Products, it was awarded gold prizes. In 1987, they were the first in Syros to start making ice cream from fresh Syrian milk.The Confectionery, Ice Cream and Confectionery Industry manufactures and packages: Delights Halvado pies Halva Ice creams"));
            allAttributes.add(new Attributes(new GeoPoint( 37.444730,24.941987), 1, "To tsipoyradiko tes Mersinis", 21, 21, "Ermoupoli", 1,"Restaurant", " ", 1, 1, 2, 2, 2, 2 ,1, 1 , "PRICE RANGE €10 - €20 Includes: Mediterranean, Greek, Seafood, European Special diets Vegetarian Friendly, Vegan Options, Gluten Free Dishes"));
            allAttributes.add(new Attributes(new GeoPoint( 37.442737,24.945045), 1, "Giannena", 22, 22, "Ermoupoli", 1,"Fast food", " ", 1, 1, 2, 2, 2, 2 ,1, 1 , "KITCHEN Greek, Coffee Meals Breakfast, Lunch, Dinner"));
            allAttributes.add(new Attributes(new GeoPoint( 37.444169,24.942975), 1, "Chill box", 23, 23, "Ermoupoli", 1,"Frozen Yogurt Shop", "Take Away" , 1, 1, 2, 2, 2, 2 ,1, 1 ,"Includes: Fast food Special diets Gluten-free dishes Meals Late night, Pota"));
            allAttributes.add(new Attributes(new GeoPoint( 37.445259, 24.943640), 1, "Avant Garden", 24, 24, "Ermoupoli", 1,"Restaurant", " ", 1, 1, 2, 2, 2, 2 ,1, 1 , "PRICE RANGE €15 - €35 Includes: Mediterranean, European, Greek, Healthy Special diets Vegetarian Friendly, Vegan Options, Gluten Free Dishes"));
            allAttributes.add(new Attributes(new GeoPoint( 37.444367,24.943005), 1, "Kanonia", 25, 25, "Ermoupoli",1,"Pizzeria", "Fast food", 1, 1, 2, 2, 2, 2 ,1, 1 ,"Pizza Napolitana & Pinsa Romana Moderna!"));
            allAttributes.add(new Attributes(new GeoPoint(37.444090, 24.944385), 1, "Oysyra", 26, 26, "Ermoupoli", 1,"Restaurant"," ", 1, 1, 2, 2, 2, 2 ,1, 1 ," PRICE RANGE €15 - €40 Includes: Italian, Pizza, Mediterranean, European Special diets Suitable for vegetarians, vegan options"));
            allAttributes.add(new Attributes(new GeoPoint( 37.443926,24.943254), 1, "Nick’s Grill", 27, 27, "Ermoupoli", 1,"Fast Food", " ", 1, 1, 2, 2, 2, 2 ,1, 1 , "Includes: Fast food, Greek, Beer restaurants Meals Dinner POSSIBILITIES Seats"));
            allAttributes.add(new Attributes(new GeoPoint( 37.444653,24.943681), 1, "Everest", 2, 2, "Ermoupoli", 1,"Fast Food", "Cafe", 1, 1, 2, 2, 2, 2, 1, 1, "Includes: Fast food, Coffee Special diets Suitable for vegetarians, vegan options Meals Breakfast, Lunch, Brunch"));
            allAttributes.add(new Attributes(new GeoPoint(37.443530,24.943790), 1, "Plaza", 6, 6, "Ermoupoli", 1,"Cafe", "Breakfast", 1, 1, 2, 2, 2, 2,1, 1, "PRICE RANGE €7 - €15 Includes: Coffee, European, Hygiene Special diets Suitable for vegetarians"));
            allAttributes.add(new Attributes(new GeoPoint(37.444384,24.943940), 1, "Jar", 12, 12, "Ermoupoli", 1,"Cafe", "Breakfast", 1, 1, 2, 2, 2, 2, 1, 1, "Includes: Bar, Cafe,European, Greek Special diets Suitable for vegetarians, vegan options Meals Breakfast, Brunch, Drinks"));
            allAttributes.add(new Attributes(new GeoPoint(37.444119, 24.944208), 1, "Bros Wap", 13, 13, "Ermoupoli", 1,"Sandwich Shop", "Cafe", 1, 1, 2, 2, 2, 2, 1, 1, "Description: Includes: Coffee, Greek."));
            allAttributes.add(new Attributes(new GeoPoint( 37.440888,24.940725), 1, "Gregory’s", 14, 14, "Ermoupoli", 1,"Cafe", "Sandwich Shop", 1, 1, 2, 2, 3, 3, 1, 1, "KITCHEN Coffee Special diets Suitable for vegetarians Meals Breakfast."));

        }
    }