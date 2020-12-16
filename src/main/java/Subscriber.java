import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttSecurityException;

public class Subscriber implements MqttCallback {

    private final static ExecutorService THREAD_POOL = Executors.newSingleThreadExecutor();

    private final static String TOPIC = "Dentists";

    private final static String BROKER = "tcp://localhost:1883";

    private final static String USER_ID = "dentist-subscriber";

    private final IMqttClient middleware;

    public Subscriber() throws MqttException {
        middleware = new MqttClient(BROKER, USER_ID);
        middleware.connect();
        middleware.setCallback(this);
    }

    void subscribeToMessages() {
        THREAD_POOL.submit(() -> {
            try {
                middleware.subscribe(TOPIC);
            } catch (MqttSecurityException e) {
                e.printStackTrace();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void connectionLost(Throwable throwable) {
        System.out.println("Connection lost!");
       while (middleware.isConnected() == false) {

           // Reestablish connection lost
           try {
                Thread.sleep(3000);
                System.out.println("Reconnecting..");
                middleware.reconnect();

           } catch (Exception e) {
               throwable.getMessage();
           }
       }
        System.out.println("Connection to broker reestablished!");
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("topic '" + topic + "': " + message);
    }
}
