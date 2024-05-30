package com.example.mobielebeleving.RecyclerView.Story;

import android.content.Context;

import com.example.mobielebeleving.R;

import java.util.ArrayList;
import java.util.List;

public class StoryManager {
    private static Context appContext;
    private static ArrayList<Story> story = new ArrayList<>();

    private StoryManager() {
    }

    public static void setApplicationContext(Context context) {
        appContext = context;
    }

    private static void createStory() {
        story.add(new Story("story 1", appContext.getString(R.string.story1), R.drawable.uilenrots));
        story.add(new Story("story 2", appContext.getString(R.string.story2), R.drawable.droomreis));
        story.add(new Story("story 3", appContext.getString(R.string.story3), R.drawable.de_zwevende_belg));
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
