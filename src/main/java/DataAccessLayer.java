import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.*;

public class DataAccessLayer {

    /**
     * Change the filepath variable if you want to load a different JSON file.
     */
    private final String filepath = "././dentists.json";

    /**
     * Loads dentists from a JSON file.
     * Returns null if no dentist could be loaded/created from JSON file.
     *
     * @return dentistregistry or null
     */
    public DentistRegistry loadDentistRegistry() {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(filepath)) {
            Object jsonObject = jsonParser.parse(reader);
            JSONObject parser = (JSONObject) jsonObject;
            //Retrieves JSON for dentists
            JSONArray dentistsJSON = (JSONArray) parser.get("dentists");
            DentistRegistry registry = loadDentists(dentistsJSON);

            return registry;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
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

    /**
     * Saves dentistregistry to JSON file
     *
     * @param DentistRegistry
     */
    public void saveDentists(DentistRegistry dentists) {
        try (FileWriter file = new FileWriter(filepath)) {
            JSONArray dentistsJSON = new JSONArray();
            List<? extends Dentist> collection = new ArrayList<>(dentists.getDentists());
            dentistsJSON.addAll(collection);
            file.write("{\n\"dentists\": " + dentistsJSON.toJSONString() + "\n}");
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
