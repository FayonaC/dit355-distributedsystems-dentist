import org.eclipse.paho.client.mqttv3.MqttException;

public class Coordinator {

    public static void main(String[] args) throws MqttException {
/*
        //try {
            Subscriber s = new Subscriber();
            s.subscribeToMessages();

            DataAccessLayer dal = new DataAccessLayer();
            DentistRegistry messageTest = dal.loadDentistRegistry();
            Publisher p = new Publisher();
            p.sendMessage(messageTest);
            p.close();
        //} catch (Exception e) {
            ///e.printStackTrace();
        //}
*/
        DataAccessLayer dal = new DataAccessLayer();
        DentistRegistry dentistsJSON = dal.loadDentistRegistry();
        System.out.println("read: " + dentistsJSON);

        Dentist newDentist = new Dentist(1, "name", "owner", 1, "address", "city", 12.3,
                14.5, "shjgsh", "tjhsg0", "jshgj", "jafh", "gjshg");
        dentistsJSON.addDentist(newDentist);
        System.out.println("before write: " + dentistsJSON);

       dal.saveDentists(dentistsJSON);
    }
}
