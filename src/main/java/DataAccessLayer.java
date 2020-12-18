import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.parser.*;

public class DataAccessLayer {

    private static String dentistRegistry;

    /**
     * dentistRegistry changes whether the component is running in production or not.
     * If not, it will use the localhost for testing purposes.
     * @param production boolean
     */
    public DataAccessLayer(boolean production) {
        if (production) {
            dentistRegistry = "https://raw.githubusercontent.com/feldob/dit355_2020/master/dentists.json";
        } else {
            dentistRegistry = "http://localhost:8080/dentists.json";
        }
    }

    /**
     * Loads dentists from a JSON URL.
     * Returns null if no dentist could be loaded/created from JSON.
     *
     * @return dentistregistry or null
     */
    public DentistRegistry loadDentistRegistry() throws Exception {
        String out = new Scanner(new URL(dentistRegistry).openStream(), "UTF-8").useDelimiter("\\A").next();

        JSONParser jsonParser = new JSONParser();
        Object jsonObject = jsonParser.parse(out);
        JSONObject parser = (JSONObject) jsonObject;
        //Retrieves JSON for dentists
        JSONArray dentistsJSON = (JSONArray) parser.get("dentists");
        DentistRegistry registry = loadDentists(dentistsJSON);

        return registry;
    }

    /**
     * Creates an ArrayList with Dentists from JSONArray
     *
     * @param arr JSONArray
     * @return ArrayList with dentists
     */
    private DentistRegistry loadDentists(JSONArray arr) {
        ArrayList<Dentist> dentists = new ArrayList<Dentist>();

        for (Object dentist : arr) {
            JSONObject dentistObj = (JSONObject) dentist;

            long id = (Long) dentistObj.get("id");
            String dentistName = (String) dentistObj.get("name");
            String owner = (String) dentistObj.get("owner");
            long dentistNumber = (Long) dentistObj.get("dentists");
            String address = (String) dentistObj.get("address");
            String city = (String) dentistObj.get("city");

            JSONObject coordinateObj = (JSONObject) dentistObj.get("coordinate");
            JSONObject openinghoursObj = (JSONObject) dentistObj.get("openinghours");

            double latitude = (Double) coordinateObj.get("latitude");
            double longitude = (Double) coordinateObj.get("longitude");
            String monday = (String) openinghoursObj.get("monday");
            String tuesday = (String) openinghoursObj.get("tuesday");
            String wednesday = (String) openinghoursObj.get("wednesday");
            String thursday = (String) openinghoursObj.get("thursday");
            String friday = (String) openinghoursObj.get("friday");

            dentists.add(new Dentist(id, dentistName, owner, dentistNumber, address, city,
                    latitude, longitude, monday, tuesday, wednesday, thursday,
                    friday));
        }
        DentistRegistry registry = new DentistRegistry(dentists);
        return registry;
    }
}
