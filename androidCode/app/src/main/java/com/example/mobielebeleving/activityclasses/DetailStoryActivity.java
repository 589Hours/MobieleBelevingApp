package com.example.mobielebeleving.activityclasses;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mobielebeleving.R;
import com.example.mobielebeleving.RecyclerView.Location.Location;
import com.example.mobielebeleving.RecyclerView.Location.LocationManager;
import com.example.mobielebeleving.RecyclerView.Story.Story;
import com.example.mobielebeleving.RecyclerView.Story.StoryManager;

public class DetailStoryActivity extends AppCompatActivity {
    public static final String EXTRA_INFO_ID = "infoId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.detail_story);

        int id = getIntent().getExtras().getInt(EXTRA_INFO_ID);
        Story story = StoryManager.getStory(id);

        TextView storyTitel = findViewById(R.id.storyTitel);
        storyTitel.setText(story.getTitel());

        ImageView storyImage = findViewById(R.id.storyPicture);
        storyImage.setImageResource(story.getImageResourceId());

        TextView storyText = findViewById(R.id.storyText);
        storyText.setText(story.getStory());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}