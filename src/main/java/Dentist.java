public class Dentist {

    private long id;
    private String dentistName;
    private String owner;
    private long dentistNumber;
    private String address;
    private String city;
    private double latitude;
    private double longitude;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;

    public Dentist(long id, String dentistName, String owner, long dentistNumber, String address, String city,
                   double latitude, double longitude, String monday, String tuesday, String wednesday, String thursday,
                   String friday) {
        this.id = id;
        this.dentistName = dentistName;
        this.owner = owner;
        this.dentistNumber = dentistNumber;
        this.address = address;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        String idValidation = String.valueOf(id); // Coverts the long id to a String to be used for validation
        if (idValidation.matches("[0-9]{2}")) {
            this.id = id;
        }
        else {
            throw new IllegalArgumentException("Id has to be two digits long");
        }
    }

    public String getDentistName() {
        return dentistName;
    }

    public void setDentistName(String dentistName) {
        if(dentistName.matches("[a-zA-Z'-]+")) {
            throw new IllegalArgumentException("Dentist name can only consist of lowercase letters, uppercase letters, dashes, and single quotes");
        }
        this.dentistName = dentistName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        if(owner.matches("[a-zA-Z'-]+")) {
            throw new IllegalArgumentException("Owner name can only consist of lowercase letters, uppercase letters, dashes, and single quotes");
        }
        this.owner = owner;
    }

    public long getDentistNumber() {
        return dentistNumber;
    }

    public void setDentistNumber(long dentistNumber) {
        String dentistNumberValidation = String.valueOf(dentistNumber); // Coverts the long dentistNumber to a String to be used for validation
        if (dentistNumberValidation.matches("[0-9]{2}")) {
            this.dentistNumber = dentistNumber;
        }
        else {
            throw new IllegalArgumentException("Number of dentists has to be two digits long");
        }
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if(address.matches("[a-zA-Z'-]+")) {
            throw new IllegalArgumentException("Address can only consist of lowercase letters, uppercase letters, dashes, and single quotes");
        }
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        if(city.matches("[a-zA-Z'-]+")) {
            throw new IllegalArgumentException("City can only consist of lowercase letters, uppercase letters, dashes, and single quotes");
        }
        this.city = city;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        if(monday.matches("[a-zA-Z'-]+")) {
            throw new IllegalArgumentException("Monday can only consist of lowercase letters, uppercase letters, dashes, and single quotes");
        }
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        if(tuesday.matches("[a-zA-Z'-]+")) {
            throw new IllegalArgumentException("Tuesday can only consist of lowercase letters, uppercase letters, dashes, and single quotes");
        }
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        if(wednesday.matches("[a-zA-Z'-]+")) {
            throw new IllegalArgumentException("Wednesday can only consist of lowercase letters, uppercase letters, dashes, and single quotes");
        }
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        if(thursday.matches("[a-zA-Z'-]+")) {
            throw new IllegalArgumentException("Thursday can only consist of lowercase letters, uppercase letters, dashes, and single quotes");
        }
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        if(friday.matches("[a-zA-Z'-]+")) {
            throw new IllegalArgumentException("Friday can only consist of lowercase letters, uppercase letters, dashes, and single quotes");
        }
        this.friday = friday;
    }

    /*add toString*/
    @Override
    public String toString() {
        return "\n{\n" +
                "\"name\": \"" + dentistName + "\"" +
                ",\n\"owner\": \"" + owner + "\"" +
                ",\n\"dentists\": " + dentistNumber +
                ",\n\"address\": \"" + address + "\"" +
                ",\n\"city\": \"" + city + "\"" +
                ",\n\"coordinates\": {" +
                "\n\"latitude\": " + latitude +
                ",\n\"longitude\": " + longitude +
                "\n},\n\"openinghours\": {" +
                "\n\"monday\": \"" + monday + "\"" +
                ",\n\"tuesday\": \"" + tuesday + "\"" +
                ",\n\"wednesday\": \"" + wednesday + "\"" +
                ",\n\"thursday\": \"" + thursday + "\"" +
                ",\n\"friday\": \"" + friday + "\"" +
                "\n}\n}";
    }
}
