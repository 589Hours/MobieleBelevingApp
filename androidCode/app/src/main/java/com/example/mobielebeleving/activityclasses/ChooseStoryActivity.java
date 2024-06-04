package com.example.mobielebeleving.activityclasses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.mobielebeleving.R;
import com.example.mobielebeleving.RecyclerView.Story.StoryAdapter;
import com.example.mobielebeleving.RecyclerView.Story.StoryManager;

public class ChooseStoryActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener, StoryAdapter.OnItemClickListener {
    private RecyclerView storyRecyclerView;
    private StoryAdapter storyRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_story);

        StoryManager.setApplicationContext(getApplicationContext());
    }

    public void refreshStoryList(){
        if (StoryManager.amountOfStory() != 0) {
            storyRecyclerView = findViewById(R.id.StoryRecyclerView);
            storyRecyclerViewAdapter = new StoryAdapter(this,
                    StoryManager.getStory(),
                    this);
            storyRecyclerView.setAdapter(storyRecyclerViewAdapter);
            storyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        navigateToEnterCodeActivity(position, StoryManager.getStory().get(position).getImageResourceId());
    }

    @Override
    public void onItemClick(int clickedPosition, int imageResourceId) {
        navigateToEnterCodeActivity(clickedPosition, imageResourceId);
    }

    private void navigateToEnterCodeActivity(int position, int imageResourceId) {
        Intent intent = new Intent(this, DetailStoryActivity.class);
        intent.putExtra(EnterCodeActivity.EXTRA_INFO_ID, position);
        startActivity(intent);
    }
}