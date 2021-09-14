import java.util.ArrayList;

public class DoctorExists {
    ArrayList<String> DoctorsInDatabase = new ArrayList<>();

    public void setDoctorsInDatabase(String doctor) {
        DoctorsInDatabase.add(doctor);
    }

    public boolean isDoctorValid(String doc){
        return DoctorsInDatabase.contains(doc);
    }
}
