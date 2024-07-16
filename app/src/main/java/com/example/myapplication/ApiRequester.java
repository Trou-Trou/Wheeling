package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osmdroid.util.GeoPoint;


public class ApiRequester extends MapActivity{

    //String test = "https://api.openrouteservice.org/v2/directions/wheelchair?api_key=5b3ce3597851110001cf624807b19f77403342489188e3af666e207f&start=8.681495,49.41461&end=8.687872,49.420318";
    static String initurl = "https://api.openrouteservice.org/v2/directions/wheelchair?api_key=";
    static final String key = "5b3ce3597851110001cf624807b19f77403342489188e3af666e207f";
    static String startLoc = "";
    static String endLoc = "";

    Gson gson = new Gson();

    RequestQueue queue;


    public ApiRequester(Context ctx) {
        queue = Volley.newRequestQueue(ctx);
    }

    public void makeRequest(GeoPoint startDep, GeoPoint endDest, VolleyCallback callback){

        startLoc = startDep.getLongitude() + "," + startDep.getLatitude();
        endLoc = endDest.getLongitude()+","+endDest.getLatitude();

        String finalurl = initurl+key+"&start="+startLoc+"&end="+endLoc;
        Log.i("POSITION",""+finalurl);


        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                finalurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jobject = new JSONObject(response);
                            JSONArray coordinates = jobject.getJSONArray("features")
                                    .getJSONObject(0)
                                    .getJSONObject("geometry")
                                    .getJSONArray("coordinates");
                            JSONArray coordinate;
                            GeoPoint[] points = new GeoPoint[coordinates.length()];
                            double longitude, langitude;
                            for(int i = 0; i < coordinates.length(); ++i) {
                                coordinate = coordinates.getJSONArray(i);
                                longitude = coordinate.getDouble(0);
                                langitude = coordinate.getDouble(1);
                                points[i] = new GeoPoint(langitude, longitude);
                            }
                            Double totalDistance = Double.valueOf(
                              jobject.getJSONArray("features")
                                      .getJSONObject(0)
                                      .getJSONObject("properties")
                                      .getJSONObject("summary")
                                      .getDouble("distance")
                            );
                            callback.onSuccess(points, totalDistance);

                        } catch (JSONException e) {
                            Toast.makeText(ctx, "Error retrieving directions", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    System.err.println(error.toString());
                    }
                }
        );

        queue.add(stringRequest);
    }



}
