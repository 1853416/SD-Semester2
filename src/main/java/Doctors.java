import java.util.ArrayList;

public class Doctors {
    ArrayList<Doctor> doctors = new ArrayList();

    public Doctor getDocByField(String Field){
        for(int i = 0; i < doctors.size(); ++i){
            if(doctors.get(i).getField().equals(Field)){
                return doctors.get(i);
            }
        }

        return null;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Doctor doctor){
        doctors.add(doctor);
    }
}
