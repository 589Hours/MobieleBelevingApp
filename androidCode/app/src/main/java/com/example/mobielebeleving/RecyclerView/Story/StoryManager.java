package com.example.mobielebeleving.RecyclerView.Story;

import android.content.Context;

import com.example.mobielebeleving.R;

import java.util.ArrayList;

public class StoryManager {
    private static Context appContext;
    private static ArrayList<Story> story = new ArrayList<>();
    public static String test;
    private static Boolean added1,added2, added3;

    public StoryManager() {
    }

    public static void setApplicationContext(Context context) {
        appContext = context;
    }

    public static void run(){
        test = "";
       added1 = false;
       added2 = false;
       added3 = false;
    }

    public static void createStory() {
        if (test.equals("1") && !added1) {
            story.add(new Story("De Verloren Sleutel", appContext.getString(R.string.verhaal_de_verloren_sleutel), R.drawable.sleutel));
            added1 = true;
        }
        if (test.equals("2") && !added2) {
            story.add(new Story("Het ontstaan van de Uilenrots", appContext.getString(R.string.de_uilenrots_verhaal), R.drawable.uil));
            added2 = true;
        } if (test.equals("3") && !added3) {
            story.add(new Story("Het Geheim van de Verzonken Stad", appContext.getString(R.string.verhaal_het_geheim_van_de_verzonken_schat), R.drawable.verzonken_schat));
            added3 = true;
        }
    }

    public static ArrayList<Story> getStory() {
        if (story.size() == 0) {
            createStory();
        }
        return story;
    }

    public static int amountOfStory() {
        return story.size();
    }

    public static Story getStory(int id) {
        if (story.size() == 0) {
            createStory();
        }
        return story.get(id);
    }
}
