# MQTT Prerequirements
* Install [Java JDK 8 or above](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
* Install [Maven 3.5 or above](https://maven.apache.org/download.cgi)
* Install MQTT Broker (e.g. Mosquitto) and run it locally on port 1883
   * For a helpful tutorial refer to [Steve's Internet Guide](http://www.steves-internet-guide.com/install-mosquitto-broker/)
* Install [JSON-Simple](http://www.java2s.com/Code/Jar/j/Downloadjsonsimple11jar.htm)

# Installation Guide
1. Clone repository to your machine
2. Open a terminal window (e.g. Command Prompt) and move to the root folder of the repository. Enter command `mvn clean install` This will create a target folder.
3. To ensure that installation was successful, check target folder for coordinator.jar.
4. Move to target folder and enter command `java -jar coordinator.jar`. This will enable the subscriber component to start listening to the MQTT Broker and the publisher component to send messages.
5. Add json-simple.jar as an external library
    * In IntelliJ:
     1. Open the repository as a project and right click on the dentist project folder then press F4.
     2. Click on the Libraries tab and then the plus symbol, and finally Java.
     3. Locate the json-simple.jar file that you downloaded earlier, select it, then click OK twice.
    * In Eclipse:
     1. Follow instructions found [here](https://stackoverflow.com/questions/8997598/importing-json-into-an-eclipse-project/8997703#8997703) 





