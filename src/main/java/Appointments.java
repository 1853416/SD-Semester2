import java.util.ArrayList;

public class Appointments {
    ArrayList<String> appointments = new ArrayList<>();

    public Appointments(){

    }
    public boolean makeAppointment(String doctor,String patient){
        String app = doctor + "#" +patient;
        appointments.add(app);

        if(appointments.size() > 0){
            return true;
        }

        return false;
    }

    public boolean removeAppointment(String doctor){
        for(int i = 0; i < appointments.size(); ++i){
            String[] s = doctor.split("#");
            String doc = s[0];

            if(doc.equals(doctor)){
                appointments.remove(i);
                break;
            }
        }

        if(appointments.size() == 0){
            return true;
        }

        return false;
    }


}
