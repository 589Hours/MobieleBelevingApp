package com.example.mobielebeleving.activityclasses;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.mobielebeleving.MQTTHandler;
import com.example.mobielebeleving.R;
import com.example.mobielebeleving.Timer;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

public class PlayActivity extends AppCompatActivity {
    private final static String TAG = "PlayActivity";
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

        //connect with server
        String clientId = "weewoo";
        MqttAndroidClient client = new MqttAndroidClient(
                this.getApplicationContext(),
                "broker.hivemq.com:1883",
                clientId);
        //TODO fix error
        //MQTTHandler.connect(client);

        //subscribe to scanner channel
        String topic = "avanstibreda/ti/1.4/a6/scanner";
        int qos = 1;
        MQTTHandler.subscribe(client, topic, qos);

        final String MESSAGETAG = "messageReceived";
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                String totalString = "\" " + message + " \"" + "on topic: " + topic;
                Log.d(MESSAGETAG, totalString);
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
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
        builder.setPositiveButton("BEGREPEN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }
    public static void setReady(){
        buttonIsReady = true;
    }
}