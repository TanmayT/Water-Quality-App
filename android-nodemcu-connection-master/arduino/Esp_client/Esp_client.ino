/*
    This sketch sends data via HTTP GET requests to data.sparkfun.com service.
    You need to get streamId and privateKey at data.sparkfun.com and paste them
    below. Or just customize this script to talk to other HTTP servers.
*/

#include <ESP8266WiFi.h>

char ssid[] = "Kritsnam_2";
char password[] = "hydro123";
const char* host = "192.168.0.107";

const int anPin1 = 17;
const int ledPin = 16;

int val = 0; 
double distance;
int time_val;
 
void setup() {
  Serial.begin(115200);
  delay(10);

  // We start by connecting to a WiFi network

  Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);

  /* Explicitly set the ESP8266 to be a WiFi-client, otherwise, it by default,
     would try to act as both a client and an access-point and could cause
     network-issues with your other WiFi-devices on your WiFi-network. */
  WiFi.mode(WIFI_STA);
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
}

double getDistance(){
  double distance = analogRead(anPin1)*5;;
  
  Serial.print(distance);
  Serial.println(" mm");

  return distance;
}

int myTimerEvent()  
{
  // You can send any value at any time.
  // Please don't send more that 10 values per second.
  return millis()/1000;
}


void loop() {
   delay(450);
  Serial.print("connecting to ");
  Serial.println(host);

  int t1 = millis();
  // Use WiFiClient class to create TCP connections
  WiFiClient client;
  const int httpPort = 8000;
  if (!client.connect(host, httpPort)) {
    Serial.println("connection failed");
    return;
  }

  Serial.print("t1:  ");
  Serial.println(millis() - t1);
  // We now create a URI for the request
  String url = "/com/data/";
  
  distance = getDistance();
  time_val = myTimerEvent();
  String t_string = String(time_val);
  String dist_string = String(distance);
  
  url = url + "?distance=" + dist_string + "&millis=" + time_val;
  Serial.print("Requesting URL: ");
  Serial.println(url);

  Serial.print("t2:  ");
  Serial.println(millis() - t1);

  // This will send the request to the server
  client.print(String("GET ") + url + " HTTP/1.1\r\n" +
               "Host: " + host + "\r\n" +
               "Connection: close\r\n\r\n");

  Serial.print("t3:  ");
  Serial.println(millis() - t1);             
  unsigned long timeout = millis();
  while (client.available() == 0) {
    if (millis() - timeout > 200) {
      Serial.println(">>> Client Timeout !");
      client.stop();
      return;
    }
  }

  Serial.print("t4:  ");
  Serial.println(millis() - t1);
  // Read all the lines of the reply from server and print them to Serial
   String response;
//  while (client.available()) {
//    response = client.readStringUntil('\r');
//  }

  Serial.print("t5:  ");
  Serial.println(millis() - t1);
  
  if(response.indexOf("led=0") > 0)
    val = 0;
  else if(response.indexOf("led=1") > 0)
    val = 1;  
  Serial.print("val = ");
  Serial.println(val);
  digitalWrite(ledPin, val);
  Serial.println();
  Serial.println("closing connection");
  
  Serial.print("t6:  ");
  Serial.println(millis() - t1);
}

