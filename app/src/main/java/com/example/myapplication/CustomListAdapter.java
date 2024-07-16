package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter {

    private final Activity context;

    public static ArrayList<Attributes> allAttributes;

    private PreferencesManager prefManager;

    private static final String TAG = "trou-CustomListAdapter";


    public CustomListAdapter(@NonNull Activity context, int resource, @NonNull ArrayList allAttributes) {
        super(context, resource, allAttributes);

        this.allAttributes = allAttributes;
        this.context = context;

        initializePreferences();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listview_row, null, true);

        TextView txtName = rowView.findViewById(R.id.txt_name);
        TextView txtLocation = rowView.findViewById(R.id.txt_location);
        ImageView itemSampleImage = rowView.findViewById(R.id.item_sample_image);
        ImageView accessibilityIcon = rowView.findViewById(R.id.sample_accessibily_icon);
        ImageView toiletAccessIcon = rowView.findViewById(R.id.sample_toilet_access_icon);
        ImageView parkingAccessIcon = rowView.findViewById(R.id.sample_parking_access_icon);
        ImageView locationAccessibilityIcon = rowView.findViewById(R.id.sample_location_access_icon);
        ImageView imgGuide = rowView.findViewById(R.id.img_guide);
        //TextView txtDistance = rowView.findViewById(R.id.txt_distance);


        itemSampleImage.setImageResource(allAttributes.get(position).chooseImgList());
        accessibilityIcon.setImageResource(allAttributes.get(position).chooseAccessibilityIcon());
        toiletAccessIcon.setImageResource(allAttributes.get(position).chooseToiletAccessIcon());
        parkingAccessIcon.setImageResource(allAttributes.get(position).chooseParkingAccessIcon());
        locationAccessibilityIcon.setImageResource(allAttributes.get(position).chooseLocationAccessIcon());
        imgGuide.setImageResource(allAttributes.get(position).chooseGuideImg());

        txtName.setText(allAttributes.get(position).getName());
        txtLocation.setText(allAttributes.get(position).getLocation());
        //txtDistance.setText(allAttributes.get(position).getDistance());


        /*if (allAttributes.get(position).isShortlisted()) {
            imgButton.setImageResource(R.drawable.ic_remove);

        }
        else {
            imgButton.setImageResource(R.drawable.ic_add);

        }

        imgButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (allAttributes.get(position).isShortlisted()) {

                    allAttributes.get(position).setShortlisted(false);
                    imgButton.setImageResource(R.drawable.ic_remove);
                    Toast.makeText(context, "removing item at position: "+position, Toast.LENGTH_SHORT).show();
                }
                else {
                    imgButton.setImageResource(R.drawable.ic_add);
                    allAttributes.get(position).setShortlisted(true);
                    Toast.makeText(context, "shortlisting item at position: "+position, Toast.LENGTH_SHORT).show();
                }
                notifyDataSetChanged();


            }
        });*/


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i(TAG, "onClick: rowView cick listener");

                Intent i = new Intent(context, DetailActivity.class);

                // second way - JSON the whole object, pass whole object.
                Gson gson = new Gson();
                String selectedItemJSONed = gson.toJson(allAttributes.get(position));
                i.putExtra("the whole object", selectedItemJSONed);

                context.startActivity(i);
            }
        });

        imgGuide = (ImageView) rowView.findViewById(R.id.img_guide);

        imgGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: button clicked");
                Intent i = new Intent(context,MapActivity.class);

                Gson gson = new Gson();
                String selectedItemJSONed = gson.toJson(allAttributes.get(position));
                i.putExtra("the whole object", selectedItemJSONed);
                i.putExtra("PathOnClickListener", true);
                context.startActivity(i);
                //Router(location);
            }
        });



        return rowView;
    }

    private void initializePreferences() {
        prefManager = PreferencesManager.instance(context.getApplicationContext());

    }
}


