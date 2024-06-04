import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    private static ArrayList<Sensor> sensors = new ArrayList<>();
    private static ArrayList<App> apps = new ArrayList<>();

    private static int counter = 0;
    public static void main(String[] args) throws IOException {


        ServerSocket serversocket = new ServerSocket(12345);

        while(true) {
            Socket socket = serversocket.accept();

            new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())).write("Type");
            String response = null;

            while(response == null) {
                response = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
            }

            if(response.equals("app")) {
                sensors.add(new Sensor(socket));
            }

            if(response.equals("Sensor")) {
                apps.add(new App(socket));
            }
        }
    }

    //telt hoeveel sensoren er gescanned zijn
    public static void count() {
        counter++;
    }

    //dit aanroepen wanneer de tijd over is
    public void endGame() throws IOException {
        //logica voor de verhalen unlocken
        String code = "";

        for (App app: apps) {
            app.writeScore(counter);
            app.unlockStories(code);
            counter = 0;
        }
    }

    public static void turnRandomOn() {
        Random random = new Random();
        int randomInt = random.nextInt(sensors.size()-1);

        if(sensors.get(randomInt).isOn()) {
            turnRandomOn();
        }

        sensors.get(randomInt).TurnOn();
    }

}
