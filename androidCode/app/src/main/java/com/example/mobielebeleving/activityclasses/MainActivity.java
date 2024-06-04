package com.example.mobielebeleving.activityclasses;

import android.content.Intent;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    private Button playButton;

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

        playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              navigateChooseLocationActivity();
            }
        });


        ImageButton dutch = findViewById(R.id.dutchButton);
        ImageButton english = findViewById(R.id.englishButton);
        ImageButton info = findViewById(R.id.helpButton);

        LanguageManager lm = new LanguageManager(this);

        dutch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lm.updateResources("nl");
                Toast toast = Toast.makeText(MainActivity.this,"Geselecteerde taal: Nederlands!", Toast.LENGTH_LONG);
                toast.show();
            }
        });
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lm.updateResources("en");
                Toast toast = Toast.makeText(MainActivity.this,"Selected language: English!",Toast.LENGTH_LONG);
                toast.show();
            }
        });
        info.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog dialog = createDialog();
                dialog.show();
            }
        });
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
    public void navigateChooseLocationActivity() {
        Intent intent = new Intent(this, ChooseLocationActivity.class);
        startActivity(intent);
    }
}