import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

import java.nio.charset.StandardCharsets;

public class Publisher {

    private final static String TOPIC = "Dentists";

    private final static String BROKER = "tcp://localhost:1883";

    private final static String USER_ID = "dentist-publisher";

    private final IMqttClient middleware;

    public Publisher() throws MqttException {
        middleware = new MqttClient(BROKER, USER_ID);
        middleware.connect();
    }

    void close() throws MqttException {
        middleware.unsubscribe("Dentists");
        middleware.disconnect();
        middleware.close();
    }

    void sendMessage(DentistRegistry message) throws MqttPersistenceException, MqttException {
        MqttMessage mqttMessage = new MqttMessage();
        mqttMessage.setRetained(true);
        mqttMessage.setQos(1);
        String msg = message.toString();
        mqttMessage.setPayload(msg.getBytes(StandardCharsets.UTF_8)); /*move messageTest into payload*/
        middleware.publish(TOPIC, mqttMessage);
    }
}
