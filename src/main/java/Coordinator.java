import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;

public class Coordinator {

    private static boolean Production = true;

    public static void main(String[] args){
    	
        try {
            // Load the dentists for the first time
            DataAccessLayer dal = new DataAccessLayer(Production);
            DentistRegistry currentRegistry = dal.loadDentistRegistry();

            // Publish the dentists for the first time
            Publisher p = new Publisher();
            p.sendMessage(currentRegistry);
            p.close();

            // TODO: Change the while condition
            while (true) {
                // Loading a second copy of the JSON for comparison
                DataAccessLayer dal2 = new DataAccessLayer(Production);
                DentistRegistry updatedRegistry = dal2.loadDentistRegistry();

                // Checks if the current registry is the same as the potentially updated registry
                // If not, an update has been made so we publish the updated dentist registry
                if (!updatedRegistry.toString().equals(currentRegistry.toString())) {
                    Publisher p2 = new Publisher();
                    p2.sendMessage(updatedRegistry);
                    currentRegistry = updatedRegistry;
                    p2.close(); 
                } 

                Thread.sleep(10000);
            }
        } catch (RuntimeException e) {
            System.err.println("Component stopped working.");
            e.printStackTrace();
        } catch (MqttPersistenceException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MqttException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
