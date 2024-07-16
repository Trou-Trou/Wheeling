package com.example.myapplication;

//import static android.content.ContentValues.TAG;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.FolderOverlay;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.MinimapOverlay;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.util.ArrayList;
import java.util.Arrays;

public class MapActivity extends AppCompatActivity {

    private static final String TAG = "trou - MapActivity";

    private final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private MapView map = null;
    Context ctx;

    private ImageView guid_map;

    ArrayList<Attributes> testAttributes = new ArrayList<>();

    boolean mapExploration;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        getSupportActionBar().hide();

        ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        setupMap();

        /*

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        map = findViewById(R.id.map)
        map?.getMapboxMap()?.loadStyleUri(Style.MAPBOX_STREETS)
         */

        guid_map = (ImageView)findViewById(R.id.guid_button);

        guid_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: button clicked");
                Intent intent = new Intent(MapActivity.this, WebActivity.class);
                Log.i(TAG, "onClick: here ok");
                startActivity(intent);
            }
        });

    }



    public void setupMap() {

        createTestAttributes();

        map = (MapView) findViewById(R.id.map);


        map.setTileSource(TileSourceFactory.MAPNIK);

        map.setBuiltInZoomControls(true);
        map.setMultiTouchControls(true);

        //receiving data through Intent.
        Intent i = getIntent();


        // receive whole object in JSON format
        Gson gson = new Gson();
        Attributes selectedHotel = gson.fromJson(getIntent().getStringExtra("the whole object"), Attributes.class);
        Log.i(TAG, "setupMap: selectedHotel is null = "+(selectedHotel == null));
        IMapController mapController = map.getController();
        mapController.setZoom(18.5);

        GeoPoint startPoint;

        requestPermissionsIfNecessary(new String[] {
        // if you need to show the current location, uncomment the line below
         Manifest.permission.ACCESS_FINE_LOCATION,
        // WRITE_EXTERNAL_STORAGE is required in order to show the map
        Manifest.permission.WRITE_EXTERNAL_STORAGE
         });

        MyLocationNewOverlay mLocationOverlay;
        mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(ctx), map);
        mLocationOverlay.enableMyLocation();
        map.getOverlays().add(mLocationOverlay);
        GeoPoint temp = mLocationOverlay.getMyLocation();
        if (selectedHotel == null) {
            // .. idanika prepei na ftiajeis GeoPoint object me syntetagmenes tou xristi
            mapExploration = true;
            startPoint = new GeoPoint(37.463493, 24.916088);
            if (temp != null) startPoint = temp;
        } else  if (getIntent().getBooleanExtra("PathOnClickListener", false)) {
            GeoPoint endpoint = new GeoPoint(selectedHotel.getGeoPoint());
            startPoint = new GeoPoint(37.445400, 24.941843);
            Router(endpoint);
            mapExploration = false;

        }else {
            mapExploration = false;
            startPoint = selectedHotel.getGeoPoint();
        }

        mapController.setCenter(startPoint);

        CompassOverlay mCompassOverlay;
        mCompassOverlay = new CompassOverlay(ctx, new InternalCompassOrientationProvider(ctx), map);
        mCompassOverlay.enableCompass();
        map.getOverlays().add(mCompassOverlay);

        MinimapOverlay mMinimapOverlay;

        mMinimapOverlay = new MinimapOverlay(ctx, map.getTileRequestCompleteHandler());
        mMinimapOverlay.setWidth(200);
        mMinimapOverlay.setHeight(200);

        map.getOverlays().add(mMinimapOverlay);

        addItemsOnMap();
    }

    public void addItemsOnMap() {
        FolderOverlay poiMarkers = new FolderOverlay(this);
        map.getOverlays().add(poiMarkers);

        Marker itemMarker;

        ArrayList<Attributes> allAttributes = CustomListAdapter.allAttributes;

        if (mapExploration == true) {
            for (Attributes attribute : testAttributes) {

                itemMarker = new Marker(map);
                itemMarker.setTitle(attribute.getTypePrimary());
                itemMarker.setSnippet(attribute.getName());
                itemMarker.setPosition(attribute.getGeoPoint());
                itemMarker.setIcon(getResources().getDrawable(attribute.getPinResourceID()));
                itemMarker.setImage(getResources().getDrawable(attribute.chooseImgList()));
                poiMarkers.add(itemMarker);
            }
        }
        else {
            for (Attributes attribute : allAttributes) {

                itemMarker = new Marker(map);
                itemMarker.setTitle(attribute.getTypePrimary());
                itemMarker.setSnippet(attribute.getName());
                itemMarker.setPosition(attribute.getGeoPoint());
                itemMarker.setIcon(getResources().getDrawable(attribute.getPinResourceID()));
                itemMarker.setImage(getResources().getDrawable(attribute.chooseImgList()));
                poiMarkers.add(itemMarker);
//            itemMarker.set(getResources().getDrawable(attribute.getAccessibilityIcon()));
//            itemMarker.setIcon(getResources().getDrawable(attribute.getToiletAccessIcon()));
//            itemMarker.setIcon(getResources().getDrawable(attribute.getParkingAccessIcon()));
//            itemMarker.setIcon(getResources().getDrawable(attribute.getLocationAccessIcon()));
            }
        }

        //

        //"cast" map items to overlay items
        ArrayList<OverlayItem> overlayItems = new ArrayList<OverlayItem>();

        if (mapExploration) {
            for (int i = 0; i < testAttributes.size(); i++) {
                overlayItems.add(new OverlayItem(testAttributes.get(i).getName(), testAttributes.get(i).getTypePrimary(), testAttributes.get(i).getGeoPoint()));
            }
        }
        else {
            for (int i = 0; i < allAttributes.size(); i++) {
                overlayItems.add(new OverlayItem(allAttributes.get(i).getName(), allAttributes.get(i).getTypePrimary(), allAttributes.get(i).getGeoPoint()));
            }
        }


        ItemizedIconOverlay itemizedIconOverlay = new ItemizedIconOverlay(overlayItems, getResources().getDrawable(R.drawable.ic_location), new ItemizedIconOverlay.OnItemGestureListener() {
            @Override
            public boolean onItemSingleTapUp(int index, Object item) {
                Log.v("Item click", "Item click");
                Log.v("debug",""+index);
                return false;
            }

            @Override
            public boolean onItemLongPress(int index, Object item) {
                Log.v("Item long click", "Item long click");
                return false;
            }
        }, map.getContext());

        map.getOverlays().add(itemizedIconOverlay);


        // refresh the map
        map.invalidate();
    }

    @Override
    public void onResume() {
        super.onResume();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        map.onResume(); //needed for compass, my location overlays, v6.0.0 and up
    }

    @Override
    public void onPause() {
        super.onPause();
        //this will refresh the osmdroid configuration on resuming.
        //if you make changes to the configuration, use
        //SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Configuration.getInstance().save(this, prefs);
        map.onPause();  //needed for compass, my location overlays, v6.0.0 and up
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (int i = 0; i < grantResults.length; i++) {
            permissionsToRequest.add(permissions[i]);
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    private void requestPermissionsIfNecessary(String[] permissions) {
        ArrayList<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted
                permissionsToRequest.add(permission);
            }
        }
        if (permissionsToRequest.size() > 0) {
            ActivityCompat.requestPermissions(
                    this,
                    permissionsToRequest.toArray(new String[0]),
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    private void Router(GeoPoint endloc){
        ApiRequester requester = new ApiRequester(ctx);
        requester.makeRequest(
                new GeoPoint(37.44514349311825, 24.94185948381832 ),
                endloc,
                new VolleyCallback() {
                    @Override
                    public void onSuccess(GeoPoint[] geoPoints, Double totalDistance) {
                        Polyline p = new Polyline();
                        p.setPoints(Arrays.asList(geoPoints));
                        p.setVisible(true);
                        p.setEnabled(true);

                        map.getOverlays().add(p);
                        map.invalidate();
                        IMapController controller = map.getController();
                        controller.setCenter(geoPoints[0]);
                    }
                }
        );

    }


   // https://jar-download.com/artifacts/org.osmdroid/osmdroid-android/4.2/source-code/org/osmdroid/views/overlay/PathOverlay.java

    /*
    1. kane kapos o xrhsths na mporei na ftiaksei ena route (?)
    2. perneis to shmeio toy xrhsth x
    3. perneis kai tiw syntetagmenes tou shmioy poy thelei to route x
    4. https://openrouteservice.org/dev/#/api-docs/v2/directions/{profile}/get
    tha trexeis ton kodika aytou tou site
    5. diavazeis grammh grammh to json pou tha sou epistrefei
    6. kai sxediazeis pano thn grammh
    7. OPENROUTESERVICE API key     5b3ce3597851110001cf624807b19f77403342489188e3af666e207f
     */

    private void createTestAttributes() {
        // Hotels
        Attributes stu1 = new Attributes(new GeoPoint(37.442689, 24.945352), 1, "Hermes", 34, 34, "Ermoupoli",1,"Hotel","", 2, 2, 1, 1, 1, 1, 1, 1, " Ermis Hotel is the largest hotel in Ermoupolis and is housed in one of the historical neoclassical buildings of the island. It is in an extremely privileged position, on a narrow strip of land, on the right edge of the port, with the historic city center as a background. With a view of the deep blue waters of the Aegean on one side and the calm waters of the harbor on the other, the visitor is surrounded by sea.");
        testAttributes.add(stu1);
        testAttributes.add(new Attributes(new GeoPoint(37.440132, 24.940342), 1, "Esperance 1", 37, 37, "Ermoupoli", 1, "Hotel", "", 2, 2, 1, 1, 2, 2, 1, 1, "The lovely decor in pastel tones will help you relax in one of our fully renovated rooms and enjoy the sea view from your window or balcony! Start your day enjoying your coffee in our large outdoor patio or your breakfast from the buffet in the breakfast area. Esperance 1 is located on the main coastal road of Ermoupoli in the port of Syros, near the Neorion Shipyard (300 meters). We will be more than happy to serve you and help you have a pleasant stay."));
        testAttributes.add(new Attributes(new GeoPoint( 37.440308, 24.940385), 1, "Diogenis", 35, 35, "Ermoupoli", 1,"Hotel", "", 2, 2, 1, 1, 2, 2, 1, 1, "The A' category hotel Diogenis is a 19th century building, combining Neoclassical Architecture with modern technologies. In February 2015 it was completely renovated. Its 40 rooms are properly equipped to ensure the most comfortable stay possible for its customers, with modern decoration and tasteful earthy color combinations. It is the ideal accommodation proposal for those who desire quality, a family atmosphere and subtle luxury in the center of Ermoupolis."));
        testAttributes.add(new Attributes(new GeoPoint( 37.442540, 24.941870), 1, "Paladion", 36, 36, "Ermoupoli", 1,"Hotel", "", 2, 2, 1, 1, 2, 2, 1, 1, " If you want the ideal budget small hotel in Ermoupoli, you are in the right place. Welcome to Palladion Hotel, a place made for travelers like you. Palladion Hotel is a budget friendly small hotel offering flat screen TV and air conditioning in the rooms. A favorite traveler activity is nearby (National Resistance Monument (0.1 km)), making Palladion Hotel a good choice for those staying in Hermoupoli. "));
        testAttributes.add(new Attributes(new GeoPoint( 37.444908, 24.944995), 1, "Ploes", 43, 43, "Ermoupoli", 1,"Hotel", "", 2, 2, 1, 1, 2, 2, 1, 1, "Every traveler needs the right hotel to recharge. For visitors to Ermoupoli, Hotel Ploes is an excellent choice for rest and rejuvenation. Well-known for its family-friendly environment and proximity to great restaurants and attractions, Hotel Ploes makes it easy for those staying here to discover some of the best of Ermoupolis. "));
        // Coffee
        testAttributes.add(new Attributes(new GeoPoint( 37.443771,24.944354), 1, "Bloom", 5, 5, "Ermoupoli",1,"Cafe", "Bar", 1, 1, 2, 2, 2, 2, 1, 1, "KITCHEN Coffee Meals Breakfast, Lunch, Dinner, Brunch, Drinks POSSIBILITIES Packed food."));
        testAttributes.add(new Attributes(new GeoPoint(37.443530,24.943790), 1, "Plaza", 6, 6, "Ermoupoli",1,"Cafe", "Breakfast", 1, 1, 2, 2, 2, 2,1, 1, "PRICE RANGE €7 - €15 Includes: Coffee, European, Hygiene Special diets Suitable for vegetarians"));
        testAttributes.add(new Attributes(new GeoPoint( 37.443093,24.944452), 1, "Okio", 7, 7, "Ermoupoli", 1,"Cafe", "Bar", 1, 1, 2, 2, 2, 2, 1,1, "Includes: Bar, Cafe, Greek Special diets Suitable for vegetarians, vegan options Meals Breakfast, Brunch, Lunch, Drinks."));
        testAttributes.add(new Attributes(new GeoPoint( 37.442951, 24.944668), 1, "Chalandriani", 8,8, "Ermoupoli", 1,"Cafe", "Bar", 1,1, 2, 2, 2, 2, 1, 1, "PRICE RANGE €3 - €20 KITCHEN Mediterranean, Greek Meals Breakfast, Lunch, Dinner."));
        testAttributes.add(new Attributes(new GeoPoint( 37.442841, 24.944813), 1, "Boheme del mar", 9, 9, "Ermoupoli", 1,"Cafe", "Bar", 1, 1, 2, 2, 2, 2, 2, 2, "KITCHEN Greek Meals Drinks POSSIBILITIES Serves Alcohol, Waitstaff, Seating."));
        testAttributes.add(new Attributes(new GeoPoint(37.444730,24.941987), 1, "Barrio", 10, 10, "Ermoupoli", 1,"Cafe", "Bar", 1, 1 , 2, 2, 2, 2, 1, 1, "Includes: Bars, Cafes, Pubs, Diners, Bars that serve food Meals Lunch, Dinner, Brunch, Late Night, Drinks POSSIBILITIES Reservations, Outdoor Seating, Seating, Serves Alcohol, Full Bar, Wine and Beer, Digital Payments, Free Wifi, Accepts Credit Cards, Table Service"));
        testAttributes.add(new Attributes(new GeoPoint(37.443946,24.943042), 1, "Coffee island", 11, 11, "Ermoupoli", 1,"Cafe", "Take Away", 1, 1, 2, 2, 2, 2, 1, 1, "At Coffee Island, of course, you can find the best hot or cold coffee from selected varieties, with excellent cream and now also in vegan options. Yes, you will find both the Greek and the classic frappe! And if you want to try something different, you also have many options for refreshing tea such as lime, strawberry or hibiscus! And because the innovation at Coffee Island does not stop at coffee, you will also find Milkshakes with the flavor of mastic and pink pepper. What do you say, will you try it? The options we recommend from Coffee Island (Syros, Hermoupoli) are: Cappuccino, Espresso Freddo 12oz, Iced Latte 12oz."));
        testAttributes.add(new Attributes(new GeoPoint(37.444119, 24.944208), 1, "Bros Wap", 13, 13, "Ermoupoli", 1,"Sandwich Shop", "Cafe", 1, 1, 2, 2, 2, 2, 1, 1, "Description: Includes: Coffee, Greek."));
        testAttributes.add(new Attributes(new GeoPoint( 37.440888,24.940725), 1, "Gregory’s", 14, 14, "Ermoupoli", 1,"Cafe", "Sandwich Shop", 1, 1, 2, 2, 3, 3, 1, 1, "KITCHEN Coffee Special diets Suitable for vegetarians Meals Breakfast."));
        testAttributes.add(new Attributes(new GeoPoint(37.441101,24.940943), 1, "Mikel", 15, 15, "Ermoupoli", 1,"Cafe", "Take Away", 1, 1, 2, 2, 3, 3, 1, 1, "Mikel Coffee Company is not only coffee, environment and service, it is a part that has become a daily routine for thousands of customers."));
        testAttributes.add(new Attributes(new GeoPoint( 37.443867,24.944054), 1, "Megaron", 16, 16, "Ermoupoli", 1,"Cafe", " ", 3, 3,3, 3, 3, 3, 3, 3, "KITCHEN Coffee"));
        testAttributes.add(new Attributes(new GeoPoint( 37.444369,24.942912), 1, "A club", 1, 1, "Ermoupoli", 1,"Cafe", "Bar", 1, 1, 2, 2, 2, 2, 1, 1, "Cafe near the Panagouli park. You can watch weakly football and basketball matches."));
        // Food
        testAttributes.add(new Attributes(new GeoPoint( 37.444435,24.943285), 1, "Glykotexneio", 18, 18, "Ermoupoli",1,"Candy Store", "Cafe", 1, 1, 2, 2, 2, 2 ,1, 1 , "PRICE RANGE €3 - €15 KITCHEN Greek"));
        testAttributes.add(new Attributes(new GeoPoint( 37.444216,24.943077), 1, "Apergis Candy Store", 19, 19, "Ermoypoli", 1,"Candy store", " ", 1, 2, 2, 2, 2 ,1, 1 , 1,"PRICE RANGE €5 - €10 INCLUDES: Greek, Coffee Special diets Suitable for vegetarians, vegan options"));
        testAttributes.add(new Attributes(new GeoPoint( 37.443464,24.943145), 1, "Leivadaras", 20, 20, "Ermoupoli", 1,"Loykoymia", " ",1, 1, 2, 2, 2, 2 ,1, 1 , "The Livadara family started in 1923 manufacturing Turkish delights, halvad pies, halva and other sugary products. In 1933 at the Thessaloniki Exhibition and in 1936 at the Pan-Cycladic Exhibition of Traditional Products, it was awarded gold prizes. In 1987, they were the first in Syros to start making ice cream from fresh Syrian milk.The Confectionery, Ice Cream and Confectionery Industry manufactures and packages: Delights Halvado pies Halva Ice creams"));
        testAttributes.add(new Attributes(new GeoPoint( 37.444730,24.941987), 1, "To tsipoyradiko tes Mersinis", 21, 21, "Ermoupoli", 1,"Restaurant", " ", 1, 1, 2, 2, 2, 2 ,1, 1 , "PRICE RANGE €10 - €20 Includes: Mediterranean, Greek, Seafood, European Special diets Vegetarian Friendly, Vegan Options, Gluten Free Dishes"));
        testAttributes.add(new Attributes(new GeoPoint( 37.442737,24.945045), 1, "Giannena", 22, 22, "Ermoupoli", 1,"Fast food", " ", 1, 1, 2, 2, 2, 2 ,1, 1 , "KITCHEN Greek, Coffee Meals Breakfast, Lunch, Dinner"));
        testAttributes.add(new Attributes(new GeoPoint( 37.444169,24.942975), 1, "Chill box", 23, 23, "Ermoupoli", 1,"Frozen Yogurt Shop", "Take Away" , 1, 1, 2, 2, 2, 2 ,1, 1 ,"Includes: Fast food Special diets Gluten-free dishes Meals Late night, Pota"));
        testAttributes.add(new Attributes(new GeoPoint( 37.445259, 24.943640), 1, "Avant Garden", 24, 24, "Ermoupoli", 1,"Restaurant", " ", 1, 1, 2, 2, 2, 2 ,1, 1 , "PRICE RANGE €15 - €35 Includes: Mediterranean, European, Greek, Healthy Special diets Vegetarian Friendly, Vegan Options, Gluten Free Dishes"));
        testAttributes.add(new Attributes(new GeoPoint( 37.444367,24.943005), 1, "Kanonia", 25, 25, "Ermoupoli", 1,"Pizzeria", "Fast food", 1, 1, 2, 2, 2, 2 ,1, 1 ,"Pizza Napolitana & Pinsa Romana Moderna!"));
        testAttributes.add(new Attributes(new GeoPoint(37.444090, 24.944385), 1, "Oysyra", 26, 26, "Ermoupoli", 1,"Restaurant"," ", 1, 1, 2, 2, 2, 2 ,1, 1 ," PRICE RANGE €15 - €40 Includes: Italian, Pizza, Mediterranean, European Special diets Suitable for vegetarians, vegan options"));
        testAttributes.add(new Attributes(new GeoPoint( 37.443926,24.943254), 1, "Nick’s Grill", 27, 27, "Ermoupoli", 1,"Fast Food", " ", 1, 1, 2, 2, 2, 2 ,1, 1 , "Includes: Fast food, Greek, Beer restaurants Meals Dinner POSSIBILITIES Seats"));
        testAttributes.add(new Attributes(new GeoPoint( 37.444653,24.943681), 1, "Everest", 2, 2, "Ermoupoli", 1,"Fast Food", "Cafe", 1, 1, 2, 2, 2, 2, 1, 1, "Includes: Fast food, Coffee Special diets Suitable for vegetarians, vegan options Meals Breakfast, Lunch, Brunch"));
        testAttributes.add(new Attributes(new GeoPoint(37.443530,24.943790), 1, "Plaza", 6, 6, "Ermoupoli", 1,"Cafe", "Breakfast", 1, 1, 2, 2, 2, 2,1, 1, "PRICE RANGE €7 - €15 Includes: Coffee, European, Hygiene Special diets Suitable for vegetarians"));
        testAttributes.add(new Attributes(new GeoPoint(37.444384,24.943940), 1, "Jar", 12, 12, "Ermoupoli", 1,"Cafe", "Breakfast", 1, 1, 2, 2, 2, 2, 1, 1, "Includes: Bar, Cafe,European, Greek Special diets Suitable for vegetarians, vegan options Meals Breakfast, Brunch, Drinks"));
        testAttributes.add(new Attributes(new GeoPoint( 37.440888,24.940725), 1, "Gregory’s", 14, 14, "Ermoupoli", 1,"Cafe", "Sandwich Shop", 1, 1, 2, 2, 3, 3, 1, 1, "KITCHEN Coffee Special diets Suitable for vegetarians Meals Breakfast."));
        // Drinks
        testAttributes.add(new Attributes(new GeoPoint( 37.443417,24.944678), 1, "Podilato",3, 3, "Ermoupoli", 1,"Cafe", "Cocktail-Bar", 1, 1, 2, 2, 1,1,1,1, "PRICE RANGE €7 - €15 Includes: Coffee, European, Hygiene Special diets Suitable for vegetarians."));
        testAttributes.add(new Attributes(new GeoPoint(37.444733,24.943964), 1, "Kouchico", 4, 4, "Ermoupoli", 1,"Cafe", "Bar", 1,1, 2, 2, 2, 2, 1, 1, "PRICE RANGE €4 - €15 Meals Drinks POSSIBILITIES Outdoor Seating, Seating, Wheelchair Accessible, Serves Alcohol, Full Bar, Accepts American Express, Accepts Mastercard, Accepts Visa, Digital Payments, Free WiFi, Accepts Discover, Accepts Credit Cards, Table Service, Wine and Beer, Dog Friendly."));
        testAttributes.add(new Attributes(new GeoPoint( 37.443771,24.944354), 1, "Bloom", 5, 5, "Ermoupoli", 1,"Cafe", "Bar", 1, 1, 2, 2, 2, 2, 1, 1, "KITCHEN Coffee Meals Breakfast, Lunch, Dinner, Brunch, Drinks POSSIBILITIES Packed food."));
        testAttributes.add(new Attributes(new GeoPoint(37.446742,24.947646), 1, "Ciel", 17, 17, "Ermoupoli", 1,"Beach-Cafe", "Beach-Bar",2, 2, 2, 2, 1, 1, 2, 2, "Includes: Bar, Cafe, Mediterranean, European, Greek, Seafood Special diets Vegan options Meals Breakfast, Lunch, Dinner"));
        // Museum
        testAttributes.add(new Attributes(new GeoPoint(37.447083,24.943806), 1,"Kyveli Institute", 29, 29, "Ermoupoli", 1,"Public Space", "Museum", 1, 1, 2, 2, 2, 2, 1, 1, "The Kyveli Institute is a legal entity under private law of a non-profit nature. It was founded in 1999 with the aim of rescuing, researching, exploiting and highlighting historical material related to the Greek and European theater of the 20th century, through the life and work of the great actress Kyveli and her family."));
        testAttributes.add(new Attributes(new GeoPoint(37.438023,24.934601), 1, "Industrial Museum", 28, 28, "Ermoupoli", 1,"Public Space", "Museum", 1, 1, 2, 2, 2, 2, 1, 1, "The Industrial Museum of Ermoupolis was founded in 2000 by the Center for Technical Culture and is housed in 4 industrial buildings of the 19th century, the Kornilaki Tannery, the Anairousi Leatherworks, the Velissaropoulou Textiles and the Katsimantis Dyeing Factory opposite the Syros General Hospital. The museum houses a collection of tools, equipment and machinery of the golden industrial age from workshops of confectionery, textile, tannery, printing, mechanical engineering, glassmaking, as well as a digital image and sound archive with workers' testimonies, maps and plans, a 3D visualization of industrial buildings and finds from the wreck of the steamer Patris."));
        testAttributes.add(new Attributes(new GeoPoint(37.445167,24.942292), 1, "Archaeological Museum", 30, 30, "Ermoupoli", 1,"Public Space", "Museum", 1, 1, 2, 2, 2, 2, 1, 1, "The Archaeological Museum of Syros was founded in 1834 and is one of the oldest museums in Greece. Since 1899, it has been housed in 4 rooms of the Ermoupolis Town Hall and offers an independent entrance from the historical Miaouli Square. The museum's collections include exhibits such as the Depas Amphikypellon, a 15 cm high tubular cup from the late 3rd millennium, Pyxida, a 7 cm high angel from the second half of the 3rd millennium, the marble figurine of a woman  from 730 BC, as well as other Proto-Cycladic and Byzantine exhibits, sculptures and inscriptions. There is also an exhibition of archaeologist Christos Tsoundas in the museum."));
        // Public Services
        testAttributes.add(new Attributes(new GeoPoint(37.445507,24.943623), 1, "Theatre Apollon", 31, 31, "Ermoupoli", 1,"Public Building", "Theatre", 2, 2, 2, 2, 2, 2, 1, 1, "The Municipal Theater of Apollo is an emblem of Ermoupolis and is inextricably linked with its history. The city, a creation of refugees at the beginning of the 19th century, was a social, economic and architectural miracle, with an intellectual movement and radiation surprising for the time."));
        testAttributes.add(new Attributes(new GeoPoint(37.445064,24.942736), 1, "Ermoupolis Town Hall", 32, 32, "Ermoupoli",1,"Town Hall", " ", 2, 2, 2, 2, 2, 1, 1, 1,"The Town Hall of Ermoupoli, which adorns Miaouli Square, is one of the largest and most imposing town halls in Greece. It was built in 1876 under the supervision of Ernesto Ziller and its cost is estimated at around 1,300,000 drachmas, an enormous amount for the time! It is a very special building with a three-story view from the square side and two-story from the back side which has impressive details, two wings with five vertical windows on the right and left, ledges, towers and in general a characteristic architecture inspired by 3 different rhythms."));
        // Shops
        testAttributes.add(new Attributes(new GeoPoint( 37.44353613122226, 24.94203488231233), 1, "Gas Mask", 38, 38, "Ermoupoli", 1,"Clothing", "Fashion store", 1, 1, 2, 2, 2, 2, 1, 1, ""));
        testAttributes.add(new Attributes(new GeoPoint( 37.444027836423096, 24.943429386382075), 1, "Hondos Center", 39, 39, "Ermoupoli", 1,"Cosmetics", "Shop", 1, 1, 2, 2, 2, 2, 1, 1, ""));
        testAttributes.add(new Attributes(new GeoPoint( 37.445071701204185, 24.94166923591363), 1, "Themelio", 40, 40, "Ermoupoli", 1,"Arts ", "Crafts", 1, 1, 2, 2, 2, 2, 1, 1, "Themelioshop.gr is a model online store, which with more than 20 years of experience, we address individuals as well as professionals and we base our operation on completely personalized online services to our customers."));
        testAttributes.add(new Attributes(new GeoPoint( 37.443865062268344, 24.944396734309382), 1, "La Maison", 41, 41, "Ermoupoli", 1,"Household", "supplies", 1, 1, 2, 2, 2, 2, 1, 1, ""));
        testAttributes.add(new Attributes(new GeoPoint( 37.444653,24.943681), 1, "Franca", 42, 42, "Ermoupoli", 1,"Hair", "Salon", 1, 1, 2, 2, 2, 2, 1, 1, ""));

//        ApiRequester requester = new ApiRequester(ctx);
//        requester.makeRequest(testAttributes.get(11).getGeoPoint(), testAttributes.get(20).getGeoPoint(), new VolleyCallback() {
//            @Override
//            public void onSuccess(GeoPoint[] geoPoints, Double totalDistance) {
//                Polyline p = new Polyline();
//                p.setPoints(Arrays.asList(geoPoints));
//                p.setVisible(true);
//                p.setEnabled(true);
//
//                map.getOverlays().add(p);
//                map.invalidate();
//                IMapController controller = map.getController();
//                controller.setCenter(geoPoints[0]);
//
//            }
//        });

    }


}


