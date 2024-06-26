package com.example.mobielebeleving.activityclasses;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import com.example.mobielebeleving.RecyclerView.Location.Location;
import com.example.mobielebeleving.RecyclerView.Location.LocationManager;
import com.example.mobielebeleving.R;

public class EnterCodeActivity extends AppCompatActivity {
    public static final String EXTRA_INFO_ID = "infoId";
    private final static String tag = EnterCodeActivity.class.getSimpleName();
    private TextView invaledCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.enter_code);

        invaledCode = findViewById(R.id.wrongcode);

        EditText codeInput = findViewById(R.id.codeInput);
        Button submitButton = findViewById(R.id.submitButton);

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
                if(checkCode(code)) {
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

    public Boolean checkCode(String code) {
        Log.d(tag, code);
        invaledCode = findViewById(R.id.wrongcode);
        if ("123".equals(code)) {
            invaledCode.setVisibility(View.GONE);
            return true;
        } else {
            Log.d(tag, "Invalid code");
            invaledCode.setVisibility(View.VISIBLE);
            return false;
        }
    }

    public void navigateToPlayActivity() {
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
    }

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
        builder.setTitle("Uitleg");
        builder.setMessage(R.string.helpDialog);
        builder.setPositiveButton(R.string.oké, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }
}