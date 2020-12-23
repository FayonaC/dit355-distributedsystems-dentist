import java.util.Date;
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
                middleware.subscribe(TOPIC, 1);
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
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0;

        while (middleware.isConnected() == false && elapsedTime < 60 * 1000) {
            // reestablish lost connection
            try {
                Thread.sleep(3000);
                System.out.println("Reconnecting..");
                middleware.reconnect();
                elapsedTime = (new Date()).getTime() - startTime;

            } catch (Exception e) {

            }
        }
        if (middleware.isConnected() == false) {
            try {
                System.out.println("Tried reconnecting for 1 minute, now disconnecting..");
                middleware.unsubscribe("Dentists");
                middleware.disconnect();
                middleware.close();
                System.out.println("Dentist RIP :(");
                System.out.println("Please restart broker and component");

            } catch (
                    MqttException mqttException) {
                throwable.getMessage();
            }
        }
        if (middleware.isConnected() == true) {
            try {
                middleware.subscribe("Dentists");
                System.out.println("Connection to broker reestablished!");
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("topic '" + topic + "': " + message);
    }
}
