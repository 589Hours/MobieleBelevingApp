package com.example.mobielebeleving.activityclasses;

import android.content.Intent;
import android.os.Bundle;
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

import com.example.mobielebeleving.CodeChecker;
import com.example.mobielebeleving.RecyclerView.Location;
import com.example.mobielebeleving.RecyclerView.LocationManager;
import com.example.mobielebeleving.R;

public class EnterCodeActivity extends AppCompatActivity {
    public static final String EXTRA_INFO_ID = "infoId";
    private final static String tag = EnterCodeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.enter_code);

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
                String code = codeInput.getText().toString();
                codeChecker.setCode(code);
                if(codeChecker.checkCode(v.getContext())) {
                    navigateToPlayActivity();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void navigateToPlayActivity() {
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }
}