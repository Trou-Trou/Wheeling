package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "trou - DetailActivity";

    TextView detailItemTitle;
    ImageView detailedHeader;
    TextView info;
    TextView type;
    ImageView accessibility;
    ImageView toiletAccess;
    ImageView parkingAccess;
    ImageView locationAccess;
    TextView accessibilityComment;
    TextView toiletAccessComment;
    TextView parkingAccessComment;
    TextView locationAccessComment;
    private Button roadmap_button;
    private Button guid_button;
    ImageView image_home, image_assistant;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getSupportActionBar().hide();

        detailedHeader = findViewById(R.id.detailed_header);
        detailItemTitle = findViewById(R.id.txt_detailed_name);
        info = findViewById(R.id.txtInfo);
        type = findViewById(R.id.txt_type);
        accessibility = findViewById(R.id.sample_accessibily_icon);
        toiletAccess = findViewById(R.id.sample_toilet_access_icon);
        parkingAccess = findViewById(R.id.sample_parking_access_icon);
        locationAccess = findViewById(R.id.sample_location_access_icon);
        accessibilityComment = findViewById(R.id.sample_accessibily_comment);
        toiletAccessComment = findViewById(R.id.sample_toilet_access_comment);
        parkingAccessComment = findViewById(R.id.sample_parking_access_comment);
        locationAccessComment = findViewById(R.id.sample_location_access_comment);

        //receiving data through Intent.
        Intent i = getIntent();


        // receive whole object in JSON format
        Gson gson = new Gson();
        Attributes selectedHotel = gson.fromJson(getIntent().getStringExtra("the whole object"), Attributes.class);

        detailItemTitle.setText(selectedHotel.getName());
        detailedHeader.setImageResource(selectedHotel.getImgDetail());
        info.setText(selectedHotel.getInfo());
        type.setText(selectedHotel.getTypePrimary());
        accessibility.setImageResource(selectedHotel.getAccessibilityIcon());
        toiletAccess.setImageResource(selectedHotel.getToiletAccessIcon());
        parkingAccess.setImageResource(selectedHotel.getParkingAccessIcon());
        locationAccess.setImageResource(selectedHotel.getLocationAccessIcon());
        accessibilityComment.setText(selectedHotel.getAccessibilityComment());
        toiletAccessComment.setText(selectedHotel.getToiletAccessComment());
        parkingAccessComment.setText(selectedHotel.getParkingAccessComment());
        locationAccessComment.setText(selectedHotel.getLocationAccessComment());

       roadmap_button = (Button) findViewById(R.id.roadmap_button);

        roadmap_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: button clicked");
                Intent intent = new Intent(DetailActivity.this, MapActivity.class);

                // second way - JSON the whole object, pass whole object.
                Gson gson = new Gson();
                String selectedItemJSONed = gson.toJson(selectedHotel);

                intent.putExtra("the whole object", selectedItemJSONed );

                startActivity(intent);
            }
        });

        image_home = (ImageView) findViewById(R.id.img_home);

        image_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: button clicked");
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

//        image_assistant = (ImageView) findViewById(R.id.img_assist);
//
//        image_assistant.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i(TAG, "onClick: button clicked");
//                Intent intent = new Intent(DetailActivity.this, AssistantActivity.class);
//                startActivity(intent);
//            }
//        });

    }
}