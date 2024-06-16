/*
 * This ESP32 code is created by esp32io.com
 *
 * This ESP32 code is released in the public domain
 *
 * For more detail (instruction and wiring diagram), visit https://esp32io.com/tutorials/esp32-light-sensor
 */

 #include <WiFi.h>
//#include <WiFiClientSecure.h>

#define LIGHT_SENSOR_PIN 34  // ESP32 pin GIOP36 (ADC0)
#define LED_PIN 13        // Led light

const int LINE_LENGTH = 16;

//hotspot Luuk
// const char* WLAN_SSID = "Luuk";
// const char* WLAN_ACCESS_KEY = "123pannekoek";

//hotspot (werkt niet)
//const char* WLAN_SSID = "rychardus";
//const char* WLAN_ACCESS_KEY = "wifishit";

//wifi Rik thuis
const char* WLAN_SSID = "vanham";
const char* WLAN_ACCESS_KEY = "20F!garoenGepetto23";

//Rick wifi thuis
// const char* WLAN_SSID = "TMNL-6FF731";
// const char* WLAN_ACCESS_KEY = "5ASQGA8CGPS4SHY3";

const char* host = "192.168.178.165";
const uint16_t port = 12345;

WiFiClient wifiClient;


bool active = true;

void setup() {
  // initialize serial communication at 9600 bits per second:
  Serial.begin(115200);

  WiFi.begin(WLAN_SSID, WLAN_ACCESS_KEY);
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(1000);
  }
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());

      if (!wifiClient.connect(host, port)){
    Serial.print("Connection to server failed");
      delay(5000);
      setup();
    } else {
      Serial.println("Connection to server successful!");
      wifiClient.write("s");
    }

  pinMode(LED_PIN, OUTPUT);
  pinMode(LIGHT_SENSOR_PIN, INPUT);
  // Om een met TLS beveiligde verbinding te kunnen gebruiken zonder certificaten
  //wifiClient.setInsecure(); // Skip de controle, niet ideaal maar werkt voorlopig wel


}


void loop() {

  int analogValue = analogRead(LIGHT_SENSOR_PIN);
  
  // reads the input on analog pin (value between 0 and 4095)
 if(active) {
    digitalWrite(LED_PIN, HIGH);

    if(analogValue > 4000) {
     active = !active;
     digitalWrite(LED_PIN, LOW);
     wifiClient.write("z");
    }
        // Serial.print("Analog read: ");
        // Serial.println(analogValue);
   } else {
    const char received = wifiClient.read();

    if(received == 'O'){
      Serial.println("received command to turn on");
      active = true;
    }
   }

  // Serial.println(analogValue);
  delay(50);
}
