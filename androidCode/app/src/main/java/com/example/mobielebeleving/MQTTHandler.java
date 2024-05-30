package com.example.mobielebeleving;

import android.util.Log;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;

public class MQTTHandler {
    private final static String TAG = "MQTTHandler";
    public static void connect(MqttAndroidClient client){
        try{
            IMqttToken token = client.connect();
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, "connect onSucces");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.d(TAG, "connect onFailure");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    public static void subscribe(MqttAndroidClient client, String topic, int qos){
        try{
            IMqttToken subToken = client.subscribe(topic, qos);
            subToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, "subscribe onSucces");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    Log.d(TAG, "subscribe onFailure");
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
