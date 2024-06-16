package com.example.mobielebeleving.RecyclerView.Story;

public class Story {
    private String titel;
    private String story;
    private int imageResourceId;

    public Story(String titel, String story, int imageResourceId) {
        this.titel = titel;
        this.story = story;
        this.imageResourceId = imageResourceId;
    }

    public String getTitel() {
        return this.titel;
    }

    public String getStory() {
        return this.story;
    }

    public int getImageResourceId() {
        return this.imageResourceId;
    }

    public String toString() {
        return this.story;
    }

}
