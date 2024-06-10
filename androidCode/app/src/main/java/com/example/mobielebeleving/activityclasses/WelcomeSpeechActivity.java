package com.example.mobielebeleving.activityclasses;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mobielebeleving.R;

public class WelcomeSpeechActivity extends AppCompatActivity {
    private Button playButton;
    private Button storyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcome_speech);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView welcomeText = findViewById(R.id.welcomeText);
        welcomeText.setText(R.string.openingText);
        welcomeText.setMovementMethod(new ScrollingMovementMethod());

        playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToChooseLocationActivity();
            }
        });

        storyButton = findViewById(R.id.storyButton);
        storyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToChooseStoryActivity();
            }
        });

    }
    public void navigateToChooseLocationActivity() {
        Intent intent = new Intent(this, ChooseLocationActivity.class);
        startActivity(intent);
    }

    public void navigateToChooseStoryActivity() {
        Intent intent = new Intent(this, ChooseStoryActivity.class);
        startActivity(intent);
    }

}