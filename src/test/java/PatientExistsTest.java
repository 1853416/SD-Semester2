import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.assertTrue;

public class PatientExistsTest {
    private PatientExists subject;
    private DoctorExists subject2;
    private Doctor doctor;
    private Patient patient;
    @Before
    public void setup() {
        subject = new PatientExists();
        subject2 = new DoctorExists();
        doctor = new Doctor("jackxnian@gmail.com","Dentist","Xin","Nian",
                "971202","Phd",69,"0824630844","9911290195083");
        patient = new Patient("test@user.co.za","Test","Er","123131321321321",
                "0123456788 ","6655554462313");
        subject.setPatientsInDatabase(patient);
        subject2.setDoctorsInDatabase(doctor);
    }

    @Test
    public void testGetMessage() {
        assertTrue(subject.isPatientValid(patient));
        assertTrue(subject2.isDoctorValid(doctor));
    }
}
