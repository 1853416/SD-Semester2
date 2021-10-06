import java.time.LocalDate;
import java.util.Date;

public class Appointment {
    Patient patient;
    Doctors doctors = new Doctors();
    public Appointment(){
        //Initialize list of doctors for patient to choose from
       Doctor doctor = new Doctor("jackxnian@gmail.com","Dentist","Xin","Nian",
                "971202","Phd",69,"0824630844","9911290195083");
       doctors.setDoctors(doctor);

        doctor = new Doctor("demo@gmail.com","General Surgeon","Demo","Doctor",
                "971202","Bsc",2,"0123456789","9911290000101");
        doctors.setDoctors(doctor);

        doctor = new Doctor("kinetap@gmail.com","Dentist","Kineta","Padiachee",
                "820412","Master",20,"0347192393","9911290820412");
        doctors.setDoctors(doctor);

       patient = new Patient("test@user.co.za","Test","Er","123131321321321",
                "012345678888888","6655554462313");
    }

    public String FindDoc(String Field){
        Doctor doc = doctors.getDocByField(Field);

        if(doc != null){
            return "Success!";
        }
        else{
            return "Fail!";
        }

    }

    public String setAppointmentTimeAvail(String time, boolean avail){
        Doctor doc = doctors.getDocByField("Dentist");

        doc.setTimesAvailability(time,avail);

        return "Success!";
    }

    public boolean docAvailable(String time){
        Doctor doc = doctors.getDocByField("Dentist");
        return doc.isAvail(time);
    }

    public String saveFormDatabase(String t, String d){
        Doctor doc = doctors.getDocByField("Dentist");
        if(doc.isAvail(t)){
            String PatientNum = patient.getPhoneNum();
            String DoctorNum = doc.getPhoneNum();
            String time = t;
            String DateApp = d;
            String currDate = LocalDate.now().toString();

            return "Saved to Database";
        }
        else{
            return "Failed to Save";
        }
    }
}
