package com.app.utils;


import org.osmdroid.util.GeoPoint;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


public class ApiRequester {

    //String test = "https://api.openrouteservice.org/v2/directions/wheelchair?api_key=5b3ce3597851110001cf624807b19f77403342489188e3af666e207f&start=8.681495,49.41461&end=8.687872,49.420318";
    static String initurl = "https://api.openrouteservice.org/v2/directions/wheelchair?api_key=";
    static final String key = "5b3ce3597851110001cf624807b19f77403342489188e3af666e207f";



    public static GeoPoint[] getGeopointFromApi(String startDep, String endDest){



        String finalurl = initurl+key+"&start="+startDep+"&end="+endDest;
//        Log.i("POSITION",""+finalurl);

        Client client = ClientBuilder.newClient();
        Response response = client.target(finalurl)
                .request(MediaType.TEXT_PLAIN_TYPE)
                .header("Accept", "application/json, application/geo+json, application/gpx+xml, img/png; charset=utf-8")
                .get();

        System.out.println("status: " + response.getStatus());
        System.out.println("headers: " + response.getHeaders());
        System.out.println("body:" + response.readEntity(String.class));


        // features.geometry.coordinates[]
        GeoPoint temp = new GeoPoint(0,0);
        GeoPoint[] templ = new GeoPoint[1];
        templ[0] = temp;
        return templ;
    }

    public static void main(String[] args) {
        GeoPoint[] points = ApiRequester.getGeopointFromApi(37.444653+","+24.943681, 37.443865062268344+","+24.944396734309382);
    }




}
