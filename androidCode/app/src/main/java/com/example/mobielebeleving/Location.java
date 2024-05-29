package com.example.mobielebeleving;

public class Location {
    private String location;
//    private String info;
    private int imageResourceId;

    public Location(String location, int imageResourceId) {
        this.location = location;
        this.imageResourceId = imageResourceId;
    }

    public String getLocation() {
        return this.location;
    }

    public int getImageResourceId() {
        return this.imageResourceId;
    }

    public String toString() {
        return this.location;
    }

}
