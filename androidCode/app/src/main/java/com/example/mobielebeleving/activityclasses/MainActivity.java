package com.example.mobielebeleving.activityclasses;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.mobielebeleving.R;
import com.example.mobielebeleving.SocketConnect;

public class MainActivity extends AppCompatActivity {
    private final static String tag = EnterCodeActivity.class.getSimpleName();
    private Button playButton;
    private Button storyButton;

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
        ImageButton info = findViewById(R.id.helpButton);

        LanguageManager lm = new LanguageManager(this);

        dutch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToChooseLocationActivity();
                lm.updateResources("nl");
                Toast toast = Toast.makeText(MainActivity.this,"Geselecteerde taal: Nederlands!", Toast.LENGTH_LONG);
                toast.show();
                welcomeTextView();
            }
        });

        storyButton = findViewById(R.id.storyButton);
        storyButton.setOnClickListener(new View.OnClickListener() {
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lm.updateResources("en");
                Toast toast = Toast.makeText(MainActivity.this,"Selected language: English!",Toast.LENGTH_LONG);
                toast.show();
                welcomeTextView();
            }
        });
        info.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                navigateToChooseStoryActivity();
            }
        });
        ChooseStoryActivity chooseStoryActivity = new ChooseStoryActivity();
        SocketConnect socketConnect = new SocketConnect(chooseStoryActivity);
        socketConnect.createSocket();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.helpButton) {
            AlertDialog dialog = createDialog();
            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    AlertDialog createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.helpTitel);
        builder.setMessage(R.string.helpDialog);
        builder.setPositiveButton(R.string.roger, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }
    public void welcomeTextView(){
        Intent intent = new Intent(this, WelcomeSpeech.class);
        startActivity(intent);
    }
}
