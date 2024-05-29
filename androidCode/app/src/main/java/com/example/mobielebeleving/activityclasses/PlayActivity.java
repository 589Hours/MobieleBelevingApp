package com.example.mobielebeleving.activityclasses;

import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.mobielebeleving.FlashLightController;
import com.example.mobielebeleving.R;
import com.example.mobielebeleving.Timer;

public class PlayActivity extends AppCompatActivity {
    private FlashLightController flashLightController;
    private ImageButton flashButton;
    private static boolean buttonIsReady = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_play);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        flashLightController = new FlashLightController((CameraManager) getSystemService(CAMERA_SERVICE));


        flashButton = findViewById(R.id.flashButton);
        flashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonIsReady){
                    Thread flashThread = new Thread(flashLightController);
                    flashThread.start();
                    buttonIsReady = false;
                    try {
                        flashThread.join();

                        /*
                        time length is optional, but keep it above 0
                        preferably above two.
                        */
                        
                        Thread countThread = new Thread(new Timer(3));
                        countThread.start();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public static void setReady(){
        buttonIsReady = true;
    }
}