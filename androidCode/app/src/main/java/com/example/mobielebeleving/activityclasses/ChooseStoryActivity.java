package com.example.mobielebeleving.activityclasses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

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
    private TextView geenVerhalen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_story);
        geenVerhalen = findViewById(R.id.geenverhalen);

        StoryManager.setApplicationContext(getApplicationContext());
        demonstratie();
        updateStoryList();

        if (StoryManager.amountOfStory() == 0) {
            geenVerhalen.setVisibility(View.VISIBLE);
        }

//        if (StoryManager.amountOfStory() != 0) {
//            storyRecyclerView = findViewById(R.id.StoryRecyclerView);
//            storyRecyclerViewAdapter = new StoryAdapter(this,
//                    StoryManager.getStory(),
//                    this);
//            storyRecyclerView.setAdapter(storyRecyclerViewAdapter);
//            storyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        }
    }

    public void demonstratie() {
        //Ter demonstratie, mag daarna weggehaald worden
        Button verhaal1 = findViewById(R.id.button);
        Button verhaal2 = findViewById(R.id.button2);
        Button verhaal3 = findViewById(R.id.button3);
        verhaal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoryManager.test = "1";
                StoryManager.createStory();
                updateStoryList();
            }
        });
        verhaal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoryManager.test = "2";
                StoryManager.createStory();
                updateStoryList();
            }
        });
        verhaal3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StoryManager.test = "3";
                StoryManager.createStory();
                updateStoryList();
            }
        });
    }

    public void updateStoryList(){
        if (StoryManager.amountOfStory() != 0) {
            storyRecyclerView = findViewById(R.id.StoryRecyclerView);
//            storyRecyclerViewAdapter.storyUpdate(StoryManager.getStory());
            storyRecyclerViewAdapter = new StoryAdapter(this,
                    StoryManager.getStory(), this);
            storyRecyclerView.setAdapter(storyRecyclerViewAdapter);
            storyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            geenVerhalen.setVisibility(View.GONE);
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
