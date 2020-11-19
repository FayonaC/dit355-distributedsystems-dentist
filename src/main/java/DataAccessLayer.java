import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.parser.*;

public class DataAccessLayer {

    /**
     * Change the filepath variable if you what to load a different JSON file.
     */
    private final String filepath = "././dentists.json";

    /**
     * Loads dentists from a JSON file.
     * Returns null if no dentist could be loaded/created from JSON file.
     * @return parsing.Dentist or null
     */
    public DentistRegistry loadDentistRegistry() {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(filepath)) {
            Object jsonObject = jsonParser.parse(reader);
            JSONObject parser = (JSONObject) jsonObject;
            //Retrieves JSON for dentists
            JSONObject dentistsJSON = (JSONObject) parser.get("dentists");
            DentistRegistry = loadDentists(dentistsJSON);

            return new DentistRegistry();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } catch(ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates an ArrayList with Dentists from JSONArray
     * @param arr JSONArray
     * @return ArrayList with dentists
     */
    private DentistRegistry loadDentists(JSONObject arr) {
        ArrayList<Dentist> dentists = new ArrayList<Dentist>();

        for (Object dentist : arr) {
            JSONObject dentistObj = (JSONObject) dentist;

            String id = (String) dentistObj.get("id");
            String dentistName = (String) dentistObj.get("name");
            String owner = (String) dentistObj.get("owner");
            Double dentistNumber = (Double) dentistObj.get("dentists");
            String address = (String) dentistObj.get("address");
            String city = (String) dentistObj.get("city");
            Double latitude = (Double) dentistObj.get("latitude");
            Double longitude = (Double) dentistObj.get("longitude");
            String monday = (String) dentistObj.get("monday");
            String tuesday = (String) dentistObj.get("tuesday");
            String wednesday = (String) dentistObj.get("wednesday");
            String thursday = (String) dentistObj.get("thursday");
            String friday = (String) dentistObj.get("friday");

            dentists.add(new Dentist(id, dentistName, owner, dentistNumber, address, city, latitude, longitude, monday, tuesday, wednesday, thursday, friday));
        }

        return DentistRegistry;
    }
}
