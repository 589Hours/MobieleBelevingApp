package com.example.mobielebeleving.activityclasses;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class PlayActivity extends AppCompatActivity {
    private final static String TAG = "PlayActivity";
    private final static String HOST_IP = "192.168.178.165";
    private FlashLightController flashLightController;
    private static ImageButton flashButton;
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

        //have to give the activity with the countdown thread so we can use runOnUiThread()
        PlayActivity playActivity = PlayActivity.this;
        flashButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buttonIsReady){
                    Thread flashThread = new Thread(flashLightController);
                    flashThread.start();
                    buttonIsReady = false;
                    flashButton.setBackgroundColor(Color.RED);
                    try {
                        flashThread.join();

                        /*
                        time length is optional, but keep it above 0
                        preferably above two.
                        */

                        Thread countThread = new Thread(new Timer(3, v.getContext(), findViewById(R.id.countdownText), playActivity));
                        countThread.start();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        //connect with server
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        //connect to server, ip will be filled in at presentation
                        Socket socket = new Socket(HOST_IP, 12345);
                        socket.getOutputStream().write('a');
                        socket.getOutputStream().flush();

                        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                        //keep checking if app receives something from server
                        while (socket.isConnected()){
                            String line = reader.readLine();
                            Log.d(TAG, "android received " + line);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
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
    public static void setReady(){
        buttonIsReady = true;
        flashButton.setBackgroundColor(Color.GREEN);
    }
}