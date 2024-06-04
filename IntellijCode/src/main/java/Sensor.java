package main.java;

import org.eclipse.paho.client.mqttv3.MqttClient;

import java.net.Socket;
//import org.eclipse.paho.client.mqttv3.MqttClient;
//import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
//import org.eclipse.paho.client.mqttv3.MqttException;
//import org.eclipse.paho.client.mqttv3.MqttMessage;
//import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class Sensor {
    private boolean isOn;
    private String topic;
    private final Socket socket;

    public Sensor(Socket socket, int sensorNumber) {
        this.isOn = false;
        this.topic = "avanstibreda/ti/1.4/a6/s" + sensorNumber;
        this.socket = socket;

        Thread t = new Thread(this::ReadData);
        t.start();
    }

    public void ReadData() {

        while(socket.isConnected()) {
            MqttClient mqttClient = new MqttClient();

        }
    }
}
