package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "trou - MainActivity";

    private ImageView  image_food, image_public_services, image_shops, image_parking, image_map, image_coffee, image_drinks, image_hotels, image_museum, image_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        image_food = (ImageView) findViewById(R.id.img_food);

        image_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: button clicked");
                Intent intent = new Intent(MainActivity.this, FoodActivity.class);
                startActivity(intent);
            }
        });

        image_public_services = (ImageView)findViewById(R.id.img_public_services);

        image_public_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: button clicked");
                Intent intent = new Intent(MainActivity.this, PublicServicesActivity.class);
                startActivity(intent);
            }
        });

        image_shops =(ImageView) findViewById(R.id.img_shops);

        image_shops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: button clicked");
                Intent intent = new Intent(MainActivity.this, ShopsActivity.class);
                startActivity(intent);
            }
        });

        image_parking = (ImageView) findViewById(R.id.img_parking);

        image_parking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: button clicked");
                Intent intent = new Intent(MainActivity.this, ParkingActivity.class);
                startActivity(intent);
            }
        });

        image_coffee = (ImageView) findViewById(R.id.img_coffee);

        image_coffee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: button clicked");
                Intent intent = new Intent(MainActivity.this, CoffeeActivity.class);
                startActivity(intent);
            }
        });

        image_drinks = (ImageView) findViewById(R.id.img_drinks);

        image_drinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: button clicked");
                Intent intent = new Intent(MainActivity.this, DrinksActivity.class);
                startActivity(intent);
            }
        });

        image_hotels = (ImageView) findViewById(R.id.img_hotels);

        image_hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: button clicked");
                Intent intent = new Intent(MainActivity.this, HotelsActivity.class);
                startActivity(intent);
            }
        });

        image_museum = (ImageView) findViewById(R.id.img_museum);

        image_museum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: button clicked");
                Intent intent = new Intent(MainActivity.this, MuseumActivity.class);
                startActivity(intent);
            }
        });

        image_map = (ImageView)findViewById(R.id.img_map);

        image_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(MainActivity.this, MapActivity.class);
                Log.i(TAG, "onClick: button clicked");
                Intent intent = new Intent(MainActivity.this, MapActivity.class);

                Log.i(TAG, "onClick: here ok");
                startActivity(intent);
            }
        });

    }


}