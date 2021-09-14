import java.util.ArrayList;

public class PatientExists {
    ArrayList<Patient> PatientsInDatabase = new ArrayList<>();

    public void setPatientsInDatabase(Patient patient) {
        PatientsInDatabase.add(patient);
    }

    public boolean isPatientValid(Patient patient){
        return PatientsInDatabase.contains(patient);
    }
}
