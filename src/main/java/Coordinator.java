public class Coordinator {
    /*Current idea:
        Retrieve JSON at start of the component and save to a variable.
        Use a while loop that continuously retrieves JSON from the Dentist Registry and saves it to another variable.
        Then compare the two variables to see if the JSON has changed, if so, publish the changed JSON.
        This way the component doesn't publish unless there has been a change to the JSON. */
    public static void main(String[] args) {
        try {
            while (true) {
                // Load the dentists for the first time
                DataAccessLayer dal = new DataAccessLayer(false);
                DentistRegistry currentRegistry = dal.loadDentistRegistry();

                // Publish the dentists for the first time
                Publisher p = new Publisher();
                p.sendMessage(currentRegistry);
                p.close();

                Thread.sleep(540000);
            }

            /*while (true) {
                DataAccessLayer dal2 = new DataAccessLayer(false);
                DentistRegistry updatedRegistry = dal2.loadDentistRegistry();

                System.out.println("updated before if: " + updatedRegistry.toString());
                System.out.println("current before if: " + currentRegistry.toString());
                System.out.println("outcome of if: " + updatedRegistry.toString().equals(currentRegistry.toString()));
                if (!updatedRegistry.toString().equals(currentRegistry.toString())) {
                    Publisher p2 = new Publisher();
                    // System.out.println("Updated dentists: " + updatedRegistry);
                    p2.sendMessage(updatedRegistry);
                    //currentRegistry = updatedRegistry;
                    p2.close();
                    System.out.println("Published!");
                }

                System.out.println("Going to sleep now.");
                Thread.sleep(10000); // Is this needed?
                System.out.println("Sleep is over.");
            }*/
        } catch (Exception e) {
            System.err.println("RIP Dentist Component!");
            e.printStackTrace();
        }
    }
}
