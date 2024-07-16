package com.example.myapplication;
import org.osmdroid.util.GeoPoint;


public class Attributes {

    private String name;
    private int imgList;
    private int imgDetail;
    private String location;
    private String info;
    private String typePrimary;
    private String typeSecondary;
    private String distance;
    private int accessibilityIcon;
    private int toiletAccessIcon;
    private int parkingAccessIcon;
    private int locationAccessIcon;
    private int accessibilityComment;
    private int toiletAccessComment;
    private int parkingAccessComment;
    private int locationAccessComment;
    private int pinResourceID;
    private GeoPoint geoPoint;
    private int imgGuide;


    private boolean shortlisted;

    private int ID;

    public boolean isShortlisted() {
        return shortlisted;
    }

    public void setShortlisted(boolean shortlisted) {
        this.shortlisted = shortlisted;
    }

    public Attributes(GeoPoint geoPoint, int pinResourceID, String name,int imgList, int imgDetail, String location, int imgGuide, String typePrimary, String typeSecondary, int accessibilityIcon , int accessibilityComment, int toiletAccessIcon, int toiletAccessComment, int parkingAccessIcon, int parkingAccessComment, int locationAccessIcon, int locationAccessComment, String info) {

        this.name = name;
        this.imgList = imgList;
        this.imgDetail = imgDetail;
        this.location = location;
        this.imgGuide = imgGuide;
        this.accessibilityIcon = accessibilityIcon;
        this.accessibilityComment = accessibilityComment;
        this.toiletAccessIcon = toiletAccessIcon;
        this.toiletAccessComment = toiletAccessComment;
        this.parkingAccessIcon = parkingAccessIcon;
        this.parkingAccessComment = parkingAccessComment;
        this.locationAccessIcon = locationAccessIcon;
        this.locationAccessComment = locationAccessComment;
        this.typePrimary = typePrimary;
        this.typeSecondary = typeSecondary;
        this.info = info;
        this.geoPoint = geoPoint;
        this.pinResourceID = pinResourceID;

        this.shortlisted = false;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location =location;
    }

    public String getInfo() {
        return info;
    }
    public void setInfo(String info) {
        this.info = info;
    }

    public String getTypePrimary() {
        return typePrimary;
    }
    public void setTypePrimary(String typePrimary) {this.typePrimary = typePrimary; }

    public String getTypeSecondary() {
        return typeSecondary;
    }
    public void setTypeSecondary(String typeSecondary) {
        this.typeSecondary = typeSecondary;
    }

    public int getAccessibilityIcon() {
        //return accessibilityIcon;
        return (chooseAccessibilityIcon());
    }
    public void setAccessibilityIcon(int accessibilityIcon) {
        this.accessibilityIcon = accessibilityIcon;
    }
    public int getImgGuide(int imgGuide){
        return (chooseGuideImg());
    }
    public void setImgGuide(int imgGuide) {
        this.imgGuide = imgGuide;
    }

    public int chooseGuideImg() {
        if (this.imgGuide == 1) {
            return R.drawable.detailed_guid_button;
        }
        else  {
            return R.drawable.detailed_guid_button;}
    }

    public int chooseAccessibilityIcon() {
        if (this.accessibilityIcon == 1) {
            return R.drawable.accessibility_green;
        }
        else if (this.accessibilityIcon == 2) {
            return R.drawable.accessibility_red;
        }
        else  {
            return R.drawable.accessibility_default;
        }
   }

    public String getAccessibilityComment() {
        //return accessibilityComment;
        return (chooseAccessibilityComment());
    }
    public void setAccessibilityComment(int accessibilityComment) {
        this.accessibilityComment = accessibilityComment;
    }
    public String chooseAccessibilityComment() {
        if (this.accessibilityComment == 1) {
            return "There are rumps";
        }
        else if (this.accessibilityComment == 2) {
            return "there are not rumps";
        }
        else  {
            return "No comment has been added.";
        }
    }

    public int getToiletAccessIcon() {
        //return toiletAccessIcon;
        return (chooseToiletAccessIcon());
    }
    public void setToiletAccessIcon(int toiletAccessIcon) {
        this.toiletAccessIcon = toiletAccessIcon;
    }
    public int chooseToiletAccessIcon() {
        if (this.toiletAccessIcon == 1) {
            return R.drawable.accessible_toilet_green;
        }
        else if (this.toiletAccessIcon == 2) {
            return R.drawable.accessible_toilet_red;
        }
        else  {
            return R.drawable.accessible_toilet_default;
        }
    }

    public String getToiletAccessComment() {
        //return toiletAccessComment;
        return (chooseToiletAccessComment());
    }
    public void setToiletAccessComment(int toiletAccessComment) {
        this.toiletAccessComment = toiletAccessComment;
    }
    public String chooseToiletAccessComment() {
        if (this.toiletAccessComment == 1) {
            return "There is an accessible toilet";
        }
        else if (this.toiletAccessComment == 2) {
            return "There is not an accessible toilet";
        }
        else  {
            return "No comment has been added.";
        }
    }

    public int getParkingAccessIcon() {
        //return parkingAccessIcon(;
        return (chooseParkingAccessIcon());
    }
    public void setParkingAccessIcon(int parkingAccessIcon) {
        this.parkingAccessIcon = parkingAccessIcon;
    }
    public int chooseParkingAccessIcon() {
        if (this.parkingAccessIcon == 1) {
            return R.drawable.accessible_parking_green;
        }
        else if (this.parkingAccessIcon == 2) {
            return R.drawable.accessible_parking_red;
        }
        else  {
            return R.drawable.accessible_parking_default;
        }
    }

    public String getParkingAccessComment() {
        //return parkingAccessComment;
        return (chooseToiletAccessComment());
    }
    public void setParkingAccessComment(int parkingAccessComment) {
        this.parkingAccessComment = parkingAccessComment;
    }
    public String chooseParkingAccessComment() {
        if (this.parkingAccessComment == 1) {
            return "There are parking spots near the building";
        }
        else if (this.parkingAccessComment == 2) {
            return "There are not parking spots near the building";
        }
        else  {
            return "No comment has been added.";
        }
    }

    public int getLocationAccessIcon() {
        //return locationAccessIcon(;
        return (chooseLocationAccessIcon());
    }
    public void setLocationAccessIcon(int locationAccessIcon) {
        this.locationAccessIcon = locationAccessIcon;
    }
    public int chooseLocationAccessIcon() {
        if (this.locationAccessIcon == 1) {
            return R.drawable.accesible_location_green;
        }
        else if (this.locationAccessIcon == 2) {
            return R.drawable.accesible_location_red;
        }
        else  {
            return R.drawable.accesible_location_default;
        }
    }

    public String getLocationAccessComment() {
        //return locationAccessComment;
        return (chooseLocationAccessComment());
    }
    public void setLocationAccessComment(int locationAccessComment) {
        this.locationAccessComment = locationAccessComment;
    }
    public String chooseLocationAccessComment() {
        if (this.locationAccessComment == 1) {
            return "This building is accessible from your location";
        }
        else if (this.locationAccessComment == 2) {
            return "his building is not accessible from your location";
        }
        else  {
            return "No comment has been added.";
        }
    }


    public int getPinResourceID() {
        //return pinResourceID;
        return (choosePin());
    }
    public void setPinResourceID(int pinResourceID) {
        this.pinResourceID = pinResourceID;
    }

    public int choosePin() {
        if (this.pinResourceID == 1) {
            return R.drawable.ic_location;
        }
        else {
            return R.drawable.ic_push_pin;
        }
    }

    public GeoPoint getGeoPoint() {
        return geoPoint;
    }

    public void setGeoPoint(GeoPoint geoPoint) {
        this.geoPoint = geoPoint;
    }


    public int getImgDetail() {
        //return imgDetail;
        return (chooseImgDetail());
    }
    public void setImgDetail(int imgDetail) {
        this.imgDetail = imgDetail;
    }

    public int getImgList() {
        //return imgList;
        return (chooseImgList());
    }
    public void setImgList(int imgList) {
        this.imgList = imgList;
    }

    public int getID() {
        return ID;
    }

    public int chooseImgList() {

        //CoffeeActivity

        if (this.imgList == 1) {
            return R.drawable.alfa_club;
        }
        else if (this.imgList == 2) {
            return R.drawable.everest;
        }
        else if (this.imgList == 3) {
            return R.drawable.podilato_syros;
        }
        else if (this.imgList == 4) {
            return R.drawable.koutciko;
        }
        else if (this.imgList == 5) {
            return R.drawable.bloom;
        }
        else if (this.imgList == 6) {
            return R.drawable.plaza_cafe_syros;
        }
        else if (this.imgList == 7) {
            return R.drawable.okio;
        }
        else if (this.imgList == 8) {
            return R.drawable.xalandriani_syros;
        }
        else if (this.imgList == 9) {
            return R.drawable.boehmedelmar;
        }
        else if (this.imgList == 10) {
            return R.drawable.barrio;
        }
        else if (this.imgList == 11) {
            return R.drawable.coffee_island;
        }
        else if (this.imgList == 12) {
            return R.drawable.jar;
        }
        else if (this.imgList == 13) {
            return R.drawable.bros_wap;
        }
        else if (this.imgList == 14) {
            return R.drawable.grigoris;
        }
        else if (this.imgList == 15) {
            return R.drawable.mikel_syrou;
        }
        else if (this.imgList == 16) {
            return R.drawable.megaron_cafe;
        }
        else if (this.imgList == 17) {
            return R.drawable.ciel;
        }

        //FoodActivity

        else if (this.imgList == 18) {
            return R.drawable.glikotexnio;
        }
        else if (this.imgList == 19) {
            return R.drawable.apergis;
        }
        else if (this.imgList == 20) {
            return R.drawable.leivadas;
        }
        else if (this.imgList == 21) {
            return R.drawable.tsipouradiko_mirsinis;
        }
        else if (this.imgList == 22) {
            return R.drawable.giannena_syros;
        }
        else if (this.imgList == 23) {
            return R.drawable.chillbox;
        }
        else if (this.imgList == 24) {
            return R.drawable.avant_garden;
        }
        else if (this.imgList == 25) {
            return R.drawable.kanonia_pizza;
        }
        else if (this.imgList == 26) {
            return R.drawable.ousyra;
        }
        else if (this.imgList == 27) {
            return R.drawable.nicks_grill;
        }

        //MuseumActivity

        else if (this.imgList == 28) {
            return R.drawable.viomixaniko_mouseio_syros;
        }
        else if (this.imgList == 29) {
            return R.drawable.kiveli_institouto;
        }
        else if (this.imgList == 30) {
            return R.drawable.arxaiologiko_mouseio;
        }

        //PublicBuildingActivity

        else if (this.imgList == 31) {
            return R.drawable.apollon_theatro;
        }
        else if (this.imgList == 32) {
            return R.drawable.dimarxeio_syrou;
        }

        //HotelsActivity

        else if (this.imgList == 34) {
            return R.drawable.hotel_ermis;
        }
        else if (this.imgList == 35) {
            return R.drawable.diogenis_hotel;
        }
        else if (this.imgList == 36) {
            return R.drawable.paladion_hotel;
        }
        else if (this.imgList == 43) {
            return R.drawable.hotel_ploes;
        }
        else if (this.imgList == 37) {
            return R.drawable.esperance;
        }

        //ShopsActivity

        else if (this.imgList == 38) {
            return R.drawable.gas_mask;
        }
        else if (this.imgList == 39) {
            return R.drawable.hontos_center;
        }
        else if (this.imgList == 40) {
            return R.drawable.themelio_syros;
        }
        else if (this.imgList == 41) {
            return R.drawable.la_masion;
        }
        else if (this.imgList == 42) {
            return R.drawable.franca_barber;
        }

        //IfNothing

        else   {
            return R.drawable.hotel_king;
        }
    }



    public int chooseImgDetail() {

        //DetailedCoffeeActivity

        if (this.imgList == 1) {
            return R.drawable.detailed_alpha_club;
        }
        else if (this.imgList == 2) {
            return R.drawable.detailed_everest;
        }
        else if (this.imgList == 3) {
            return R.drawable.detailed_podilato;
        }
        else if (this.imgList == 4) {
            return R.drawable.detailed_koutsiko_syros;
        }
        else if (this.imgList == 5) {
            return R.drawable.detailed_bloom_syros;
        }
        else if (this.imgList == 6) {
            return R.drawable.detailed_plaza;
        }
        else if (this.imgList == 7) {
            return R.drawable.detailed_okio;
        }
        else if (this.imgList == 8) {
            return R.drawable.detailed_xalandriani;
        }
        else if (this.imgList == 9) {
            return R.drawable.detailed_boehme;
        }
        else if (this.imgList == 10) {
            return R.drawable.detailed_barrio;
        }
        else if (this.imgList == 11) {
            return R.drawable.detailed_coffee_island;
        }
        else if (this.imgList == 12) {
            return R.drawable.detailed_jar;
        }
        else if (this.imgList == 13) {
            return R.drawable.detailed_bros;
        }
        else if (this.imgList == 14) {
            return R.drawable.detailed_grigoris;
        }
        else if (this.imgList == 15) {
            return R.drawable.detailed_mikel;
        }
        else if (this.imgList == 16) {
            return R.drawable.detailed_megaron;
        }
        else if (this.imgList == 17) {
            return R.drawable.detailed_ciel;
        }

        //DetailedFoodActivity

        else if (this.imgList == 18) {
            return R.drawable.detailed_glikotexnio;
        }
        else if (this.imgList == 19) {
            return R.drawable.detailed_apergis;
        }
        else if (this.imgList == 20) {
            return R.drawable.detailed_leivadaras;
        }
        else if (this.imgList == 21) {
            return R.drawable.detailed_tsipouradiko_tis_mirsinis;
        }
        else if (this.imgList == 22) {
            return R.drawable.detailed_giannena;
        }
        else if (this.imgList == 23) {
            return R.drawable.detailed_chillbox;
        }
        else if (this.imgList == 24) {
            return R.drawable.detailed_avant_garden;
        }
        else if (this.imgList == 25) {
            return R.drawable.detailed_kanonia_pizza;
        }
        else if (this.imgList == 26) {
            return R.drawable.detailed_ousyra;
        }
        else if (this.imgList == 27) {
            return R.drawable.detailed_nicks_grill;
        }

        //DetailedMuseumActivity

        else if (this.imgList == 28) {
            return R.drawable.detailed_viomixaniko_mouseio;
        }
        else if (this.imgList == 29) {
            return R.drawable.detailed_kiveli_institouto;
        }
        else if (this.imgList == 30) {
            return R.drawable.detailed_arxaiologiko_mouseio;
        }

        //DetailedPublicBuildingActivity

        else if (this.imgList == 31) {
            return R.drawable.detailed_apollon_theatro;
        }
        else if (this.imgList == 32) {
            return R.drawable.detailed_dimarxeio;
        }

        //DetailedHotelsActivity

        else if (this.imgList == 34) {
            return R.drawable.detailed_hotel_ermis;
        }
        else if (this.imgList == 35) {
            return R.drawable.detailed_diogenis_hotel;
        }
        else if (this.imgList == 36) {
            return R.drawable.detailed_paladion_hotel;
        }
        else if (this.imgList == 43) {
            return R.drawable.detailed_hotel_ploes;
        }
        else if (this.imgList == 37) {
            return R.drawable.detailed_esperance_1;
        }

        //DetailedShopsActivity

        else if (this.imgList == 38) {
            return R.drawable.detailed_gas_mask;
        }
        else if (this.imgList == 39) {
            return R.drawable.detailed_hontos_center;
        }
        else if (this.imgList == 40) {
            return R.drawable.detailed_themelio;
        }
        else if (this.imgList == 41) {
            return R.drawable.detailed_la_masion;
        }
        else if (this.imgList == 42) {
            return R.drawable.detailed_franca_barber;
        }

        //IfDetailedNothing

        else   {
            return R.drawable.detailed_hotel_king;
        }
    }

    private String getUrlExtension() {
        String correctURLExtension = "";
        correctURLExtension += geoPoint.getLatitude() + "," + geoPoint.getLongitude();

        return correctURLExtension;
    }

}




