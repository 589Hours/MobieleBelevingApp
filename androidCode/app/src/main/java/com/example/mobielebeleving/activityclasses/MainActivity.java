package com.example.mobielebeleving.activityclasses;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mobielebeleving.LanguageManager;
import com.example.mobielebeleving.R;
import com.example.mobielebeleving.RecyclerView.Story.StoryManager;


public class MainActivity extends AppCompatActivity {
    private final static String TAG = EnterCodeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        ImageButton dutch = findViewById(R.id.dutchButton);
        ImageButton english = findViewById(R.id.englishButton);

        LanguageManager lm = new LanguageManager(this);

        dutch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lm.updateResources("nl");
                Toast toast = Toast.makeText(MainActivity.this,"Geselecteerde taal: Nederlands!", Toast.LENGTH_LONG);
                toast.show();
                welcomeTextView();
            }
        });
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lm.updateResources("en");
                Toast toast = Toast.makeText(MainActivity.this,"Selected language: English!",Toast.LENGTH_LONG);
                toast.show();
                welcomeTextView();
            }
        });

        //idk storyManager setup? (Fedde can explain more)
        StoryManager.run();
        StoryManager.setApplicationContext(this);
    }

    public void welcomeTextView(){
        Intent intent = new Intent(this, WelcomeSpeechActivity.class);
        startActivity(intent);
    }
}
