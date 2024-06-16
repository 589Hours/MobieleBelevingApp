import java.io.*;
import java.net.Socket;


public class Sensor {
    private boolean isOn;
    private final Socket socket;
    private InputStream reader;
    private DataOutputStream writer;

    public Sensor(Socket socket) throws IOException {
        this.isOn = false;
        this.socket = socket;

        this.reader = socket.getInputStream();
        this.writer = new DataOutputStream(socket.getOutputStream());

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
            char readData = (char) reader.read();

            if(readData == 'z') {
                System.out.println("scan received");
                this.isOn = false;
                Main.count();
                turnOn();
//                Main.turnRandomOn();
            }
        }

    }

    public void turnOn() {
        try {
            //send letter O van opnieuw
            writer.writeChar('O');
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.isOn = true;
    }

    public void turnOff() {
        this.isOn = false;
    }
}
