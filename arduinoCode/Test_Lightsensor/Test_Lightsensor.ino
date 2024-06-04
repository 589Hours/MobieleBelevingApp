/*
 * This ESP32 code is created by esp32io.com
 *
 * This ESP32 code is released in the public domain
 *
 * For more detail (instruction and wiring diagram), visit https://esp32io.com/tutorials/esp32-light-sensor
 */

 #include <WiFi.h>
//#include <WiFiClientSecure.h>
#include <PubSubClient.h>

#define LIGHT_SENSOR_PIN 34  // ESP32 pin GIOP36 (ADC0)
#define LED_PIN 13        // Led light

const char* MQTT_CLIENT_ID = "Rychardus";

const char* MQTT_BROKER_URL = "broker.hivemq.com";
const int   MQTT_PORT = 1883;
const char* MQTT_USERNAME = "";
const char* MQTT_PASSWORD = "";

const char* MQTT_TOPIC_SCANNER = "avanstibreda/ti/1.4/a6/scanner";
const char* MQTT_TOPIC_RECIEVER = "avanstibreda/ti/1.4/a6/reciever";


const int MQTT_QOS = 0;
const int LINE_LENGTH = 16;

//hotspot Luuk
// const char* WLAN_SSID = "Luuk";
// const char* WLAN_ACCESS_KEY = "123pannekoek";

//hotspot (werkt niet)
const char* WLAN_SSID = "rychardus";
const char* WLAN_ACCESS_KEY = "wifishit";

//Rick wifi thuis
// const char* WLAN_SSID = "TMNL-6FF731";
// const char* WLAN_ACCESS_KEY = "5ASQGA8CGPS4SHY3";

WiFiClient wifiClient;
PubSubClient mqttClient(wifiClient);


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

  pinMode(LED_PIN, OUTPUT);
  pinMode(LIGHT_SENSOR_PIN, INPUT);
  // Om een met TLS beveiligde verbinding te kunnen gebruiken zonder certificaten
  //wifiClient.setInsecure(); // Skip de controle, niet ideaal maar werkt voorlopig wel

  // Zet de MQTT client op
  mqttClient.setServer(MQTT_BROKER_URL, MQTT_PORT);
  mqttClient.setCallback(mqttCallback);
  
  // Maak verbinding met de MQTT broker
  if (!mqttClient.connect(MQTT_CLIENT_ID, MQTT_USERNAME, MQTT_PASSWORD)) {
    Serial.println("Failed to connect to MQTT broker");
  } else {
    Serial.println("Connected to MQTT broker");
  }

  if (!mqttClient.subscribe(MQTT_TOPIC_RECIEVER, MQTT_QOS)) {
    Serial.print("Failed to subscribe to topic ");
    Serial.println(MQTT_TOPIC_RECIEVER);
  } else {
    Serial.print("Subscribed to topic ");
    Serial.println(MQTT_TOPIC_RECIEVER);
  }

}

void mqttCallback(char* topic, byte* payload, unsigned int length) {
  Serial.print("MQTT callback called for topic ");
  Serial.println(topic);
  Serial.print("Payload length ");
  Serial.println(length);

  if(strcmp(topic, MQTT_TOPIC_RECIEVER) == 0) {

    // char txt[LINE_LENGTH];
    // for (int i = 0; i < LINE_LENGTH + 1; i++) { txt[i] = '\0'; }
    // strncpy(txt, (const char *) payload, length > 16 ? 16 : length);

      Serial.println("message recieved!");
      // Serial.println(txt);
    // if(txt == "1") {
      active = true;
    // }
  }
}

void loop() {

  mqttClient.loop();

  int analogValue = analogRead(LIGHT_SENSOR_PIN);
  

  // reads the input on analog pin (value between 0 and 4095)
 if(active) {
    digitalWrite(LED_PIN, HIGH);

    if(analogValue > 4000) {
    mqttClient.publish(MQTT_TOPIC_SCANNER, "1");
     active = !active;
     digitalWrite(LED_PIN, LOW);
    }
        // Serial.print("Analog read: ");
        // Serial.println(analogValue);
   }

  // Serial.println(analogValue);
  delay(50);
}
