import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ValidIDNumberTest {
    private ValidIDNumber subject;
    private Doctor doctor;
    private Patient patient;
    @Before
    public void setup() {
        doctor = new Doctor("jackxnian@gmail.com","Dentist","Xin","Nian",
                "971202","Phd",69,"0824630844","9911290195083");
        patient = new Patient("test@user.co.za","Test","Er","123131321321321",
                "0123456788 ","6655554462313");
        subject = new ValidIDNumber();
    }

    @Test
    public void testGetMessage() {
        assertTrue(subject.isIDValid(patient.getID()));
        assertTrue(subject.isIDValid(doctor.getID()));
    }
}
