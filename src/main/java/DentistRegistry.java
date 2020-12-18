import java.util.ArrayList;

public class DentistRegistry {
    private ArrayList<Dentist> dentists;

    public DentistRegistry(ArrayList<Dentist> dentists) {
        setDentists(dentists);
    }

    public ArrayList<Dentist> getDentists() {
        return dentists;
    }

    public void setDentists(ArrayList<Dentist> dentists) {
        this.dentists = dentists;
    }

    public void addDentist(Dentist dentist) {
        dentists.add(dentist);
    }

    @Override
    public String toString() {
        return "{\n" +
                "\"dentists\" : " + dentists.toString() + "\n}";
    }

}
