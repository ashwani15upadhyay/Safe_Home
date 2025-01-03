package com.safeHome.model;


public class UpdateModel {
    private String latitude;
    private String longitude;

    public UpdateModel() {
    }


    public UpdateModel(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }


    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}

