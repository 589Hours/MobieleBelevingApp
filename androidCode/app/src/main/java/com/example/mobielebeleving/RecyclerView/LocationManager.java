package com.example.mobielebeleving.RecyclerView;

import android.content.Context;

import com.example.mobielebeleving.R;

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
        locations.add(new Location(appContext.getString(R.string.droomReis), R.drawable.droomreis));
        locations.add(new Location(appContext.getString(R.string.deZwevendeBelg), R.drawable.de_zwevende_belg));
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
