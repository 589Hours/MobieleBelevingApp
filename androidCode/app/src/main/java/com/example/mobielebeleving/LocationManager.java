package com.example.mobielebeleving;

import android.content.Context;

import java.util.ArrayList;

public class LocationManager {
    private static Context appContext;
    private static ArrayList<Location> locations = new ArrayList<>();

    private LocationManager() {
    }

    public static void setApplicationContext(Context context) {
        appContext = context;
    }

    private static void createProject() {
        locations.add(new Location(appContext.getString(R.string.uilenRots), R.drawable.uilenrots));
    }

    public static ArrayList<Location> getLocations() {
        if (locations.size() == 0) {
            createProject();
        }
        return locations;
    }

    public static Location getLocations(int id) {
        if (locations.size() == 0) {
            createProject();
        }
        return locations.get(id);
    }

}
