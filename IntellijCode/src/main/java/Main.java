import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static ArrayList<Sensor> sensors = new ArrayList<>();
    private static ArrayList<App> apps = new ArrayList<>();

    private static int counter = 0;
    public static void main(String[] args) throws IOException {

        /* for now, it's console input however timer? */
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Scanner scanner = new Scanner(System.in);
                    if(scanner.nextLine().equals("end")){
                        endGame();
                    }
                }
            }
        }).start();

        ServerSocket serversocket = new ServerSocket(12345);

        while(true) {
            Socket socket = serversocket.accept();
            char character = (char) socket.getInputStream().read();
            System.out.println("Server got character: " + character);

            if(character == 's') {
                System.out.println("Sensor added");
                sensors.add(new Sensor(socket));
            }

            if (character == 'a'){
                if (apps.isEmpty()){
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (true){
                                if (!apps.isEmpty()){
                                    System.out.println("apps isn't empty");
                                    try {
                                        System.out.println("starting countdown to end game");

                                        /*
                                        set desired session length
                                        seconds * factor 1000 for milliseconds.
                                         */
                                        Thread.sleep(120 * 1000);

                                        endGame();
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                        }
                    }).start();
                }
                System.out.println("App added");
                apps.add(new App(socket));
            }
        }
    }

    //telt hoeveel sensoren er gescanned zijn
    public synchronized static void count() {
        counter++;
        updateCounter();
    }

    private synchronized static void updateCounter() {
        for (int i = 0; i < apps.size(); i++) {
            App app = apps.get(i);
            //check if someone leaves mid-game
            if (!app.isConnected()){
                apps.remove(app);
                continue;
            }
            app.writeScore(counter);
        }
    }

    //dit aanroepen wanneer de tijd over is
    public static void endGame() {
        System.out.println("endGame called");
        //logica voor de verhalen unlocken
        String code = "unlock:2";

        for (int i = 0; i < apps.size(); i++) {
            App app = apps.get(i);

            if (!app.isConnected()){
                apps.remove(app);
                continue;
            }

            app.unlockStories(code);
            counter = 0;
        }
        apps.clear();
    }

    public static void turnRandomOn() {
        Random random = new Random();
        int randomInt = random.nextInt(sensors.size()-1);

        if(sensors.get(randomInt).isOn()) {
            turnRandomOn();
        }

        sensors.get(randomInt).turnOn();
    }
}
