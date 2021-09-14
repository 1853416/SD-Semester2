import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidNameTest {
    private ValidName subject;
    private Doctor doctor;
    private Patient patient;

    @Before
    public void setup() {
        doctor = new Doctor("jackxnian@gmail.com","Dentist","Xin","Nian",
                "971202","Phd",69,"0824630844","9911290195083");
        patient = new Patient("test@user.co.za","Test123","Er","123131321321321",
                "0123456788 ","6655554462313");
        subject = new ValidName();
    }

    @Test
    public void testGetMessage() {
        assertTrue(subject.isNameValid(doctor.getName()));
        assertFalse(subject.isNameValid(patient.getName()));
    }
}
