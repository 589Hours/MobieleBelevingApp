import java.io.*;
import java.net.Socket;


public class Sensor {
    private boolean isOn;
    private String topic;
    private final Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public Sensor(Socket socket) throws IOException {
        this.isOn = false;
        this.socket = socket;

        this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        Thread t = new Thread(() -> {
            try {
                ReadData();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        t.start();
    }

    public boolean isOn() {
        return isOn;
    }

    public void ReadData() throws IOException {

        while(true) {
            String readData = reader.readLine();
            if(readData == null) {
                break;
            }

            if(readData.equals("scanned")) {
                this.isOn = false;
                Main.count();
                Main.turnRandomOn();
            }
        }

    }

    public void TurnOn() {
        this.isOn = true;
    }

    public void TurnOff() {
        this.isOn = false;
    }
}
