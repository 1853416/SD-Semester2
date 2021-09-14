import java.util.ArrayList;

public class DoctorExists {
    ArrayList<Doctor> DoctorsInDatabase = new ArrayList<>();

    public void setDoctorsInDatabase(Doctor doctor) {
        DoctorsInDatabase.add(doctor);
    }

    public boolean isDoctorValid(Doctor doc){
        return DoctorsInDatabase.contains(doc);
    }
}
