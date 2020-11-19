import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public class Publisher {

    private final static String TOPIC = "test";

    private final static String BROKER = "tcp://localhost:1883";

    private final static String USER_ID = "test-publisher";

    private final IMqttClient middleware;

    public Publisher() throws MqttException {
        middleware = new MqttClient(BROKER, USER_ID);
        middleware.connect();
    }

    public static void main(String[] args) throws MqttException {
        DataAccessLayer dal = new DataAccessLayer();
        DentistRegistry messageTest = dal.loadDentistRegistry();
        System.out.println(messageTest);
        Publisher p = new Publisher();
        p.sendMessage(messageTest);
        p.close();
    }

    private void close() throws MqttException {
        middleware.disconnect();
        middleware.close();
    }

    private void sendMessage(DentistRegistry messageTest) throws MqttPersistenceException, MqttException {
        MqttMessage message = new MqttMessage();
        message.setPayload("Hello World!".getBytes());
        middleware.publish(TOPIC, message);
    }
}
