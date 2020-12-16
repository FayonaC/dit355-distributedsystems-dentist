import java.nio.file.*;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

public class Coordinator {
    // Change this based on what directory/folder your dentists.json is in.
    private static final String dentistDirPath = "././";

    public static void main(String[] args) {
        try {
            // Load the dentists for the first time
            DataAccessLayer dal = new DataAccessLayer();
            DentistRegistry dentists = dal.loadDentistRegistry();

            // Publish the dentists for the first time
            Publisher p = new Publisher();
            System.out.println("Initial dentists: " + dentists);
            p.sendMessage(dentists);
            p.close();

            // Watcher that checks/loops for new updates to the dentists.json & publishes them
            // watchUpdates();

        } catch (Exception e) {
            System.err.println("RIP Dentist Component!");
            e.printStackTrace();
        }
    }

    /**
     * Watches the dentists.json file for updates and publishes them when the file is modified.
     * Example: https://dzone.com/articles/listening-to-fileevents-with-java-nio
     * TODO: Use Observer pattern
     * @throws Exception
     */
    private static void watchUpdates() throws Exception {
        Publisher p = new Publisher(); // Here or every time there's a change? I.e. move it to line 50

        WatchService watcher = FileSystems.getDefault().newWatchService();
        Path dir = Paths.get(dentistDirPath);
        dir.register(watcher, ENTRY_MODIFY);
        boolean poll = true;

        // Infinite loop to listen for events
        while (poll) {
            try {
                WatchKey key = watcher.take();

                for (WatchEvent<?> event : key.pollEvents()) {
                    if (event.kind() == ENTRY_MODIFY && event.context().toString().equals("dentists.json")) {
                        DataAccessLayer dal = new DataAccessLayer();
                        DentistRegistry dentists = dal.loadDentistRegistry();
                        System.out.println(dentists);
                        p.sendMessage(dentists);
                    }
                }
                Thread.sleep(1000); // Sleep to prevent duplicate publications
                poll = key.reset(); // Returns false if the key is no longer valid, exits the loop
            } catch (InterruptedException e) {
                System.err.println("RIP Dentist Watcher!");
                e.printStackTrace();
            }
        }

        p.close();
    }
}


