import java.io.*;
import java.net.Socket;

public class App {
    private BufferedWriter writer;
    private BufferedReader reader;
    private Socket socket;
    public App(Socket socket) throws IOException {
        this.socket = socket;
        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public void writeScore(int score) throws IOException {
        writer.write(score);
    }

    public void unlockStories(String unlockCode) {

    }
}
