package com.example.mobielebeleving;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private final static String tag = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        EditText codeInput = findViewById(R.id.codeInput);
        Button submitButton = findViewById(R.id.submitButton);
        CodeChecker codeChecker = new CodeChecker();
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