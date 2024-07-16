package com.example.myapplication;

import org.osmdroid.util.GeoPoint;

public interface VolleyCallback {
    void onSuccess(GeoPoint[] geoPoints, Double totalDistance);
}
