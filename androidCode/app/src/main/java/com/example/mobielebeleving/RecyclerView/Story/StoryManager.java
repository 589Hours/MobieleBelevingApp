package com.example.mobielebeleving.RecyclerView.Story;

import android.content.Context;

import com.example.mobielebeleving.R;

import java.util.ArrayList;

public class StoryManager {
    private static Context appContext;
    private static ArrayList<Story> story = new ArrayList<>();

    private StoryManager() {
    }

    public static void setApplicationContext(Context context) {
        appContext = context;
    }

    private static void createStory() {
        story.add(new Story("De Verloren Sleutel", appContext.getString(R.string.verhaal_de_verloren_sleutel), R.drawable.sleutel));
        story.add(new Story("De Magische Zandloper", appContext.getString(R.string.verhaal_de_magische_zandloper), R.drawable.zandloper));
        story.add(new Story("Het Geheim van de Verzonken Stad", appContext.getString(R.string.verhaal_het_geheim_van_de_verzonken_schat), R.drawable.verzonken_schat));
    }

    public static ArrayList<Story> getStory() {
        if (story.size() == 0) {
            createStory();
        }
        return story;
    }

    public static Story getStory(int id) {
        if (story.size() == 0) {
            createStory();
        }
        return story.get(id);
    }
}
