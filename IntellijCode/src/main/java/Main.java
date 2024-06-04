package main.java;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {
    private static ArrayList<Sensor> sensors = new ArrayList<Sensor>();
    public static void main(String[] args) throws IOException {

        ServerSocket serversocket = new ServerSocket(12345);


        while(true) {
            Socket socket = serversocket.accept();
            sensors.add(new Sensor(socket, sensors.size()+1));
        }
    }
}
