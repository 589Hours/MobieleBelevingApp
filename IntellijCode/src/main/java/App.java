import java.io.*;
import java.net.Socket;

public class App {
    private BufferedWriter writer;
    private BufferedReader reader;
    private Socket socket;
    public App(Socket socket) {

        try {
            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeScore(int score){
        try {
            //set to string so apps only have to handle strings
            String scoreString = String.valueOf(score);

            System.out.println("sent score: " + scoreString);

            writer.write(scoreString + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unlockStories(String unlockCode) {
        try {
            System.out.println("sent story code: " + unlockCode);
            writer.write(unlockCode + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean isConnected() {
        return socket.isConnected();
    }
}
