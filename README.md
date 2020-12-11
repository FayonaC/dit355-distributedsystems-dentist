# Dentist Component

## MQTT Prerequirements
* Install [Java JDK 8 or above](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
* Install [Maven 3.5 or above](https://maven.apache.org/download.cgi)
* Install MQTT Broker (e.g. Mosquitto) and run it locally on port 1883
   * For a helpful tutorial refer to [Steve's Internet Guide](http://www.steves-internet-guide.com/install-mosquitto-broker/)
* Install [JSON-Simple](http://www.java2s.com/Code/Jar/j/Downloadjsonsimple11jar.htm)

## Installation Guide
1. Clone repository to your machine
2. Open a terminal window (e.g. Command Prompt) and move to the root folder of the repository. Enter command `mvn clean install` This will create a target folder.
3. To ensure that installation was successful, check target folder for coordinator.jar.
4. Move to target folder and enter command `java -jar coordinator.jar`. This will enable the Dentist component to publish messages to the MQTT Broker.

### Warning
Currently the relative paths used in DataAccessLayer and Coordinator makes it so that you can only run the component from your IDE. 