package com.example.mobielebeleving;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_INFO_ID = "infoId";
    private final static String tag = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText codeInput = findViewById(R.id.codeInput);
        Button submitButton = findViewById(R.id.submitButton);
        CodeChecker codeChecker = new CodeChecker();

        int id = getIntent().getExtras().getInt(EXTRA_INFO_ID);
        Location location = LocationManager.getLocations(id);

        TextView projectInfo = findViewById(R.id.projectTitel);
        projectInfo.setText(location.getLocation());

        ImageView projectImage = findViewById(R.id.infoDetailImageView);
        projectImage.setImageResource(location.getImageResourceId());

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(tag, codeInput.getText().toString());
                Log.d(tag, "User clicked the button");
                String code = codeInput.getText().toString();
                codeChecker.setCode(code);
                if(codeChecker.checkCode(v.getContext())) {
                    navigateToProjectActivity();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void navigateToProjectActivity() {
        Intent intent = new Intent(this, Tijdelijke_Layout.class);
        startActivity(intent);
    }
}