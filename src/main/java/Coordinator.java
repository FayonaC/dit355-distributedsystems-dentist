import org.eclipse.paho.client.mqttv3.MqttException;

public class Coordinator {

    public static void main(String[] args) throws MqttException {
        Subscriber s = new Subscriber();
        s.subscribeToMessages();

        DataAccessLayer dal = new DataAccessLayer();
        DentistRegistry messageTest = dal.loadDentistRegistry();
        Publisher p = new Publisher();
        p.sendMessage(messageTest);
        p.close();


    }


}
