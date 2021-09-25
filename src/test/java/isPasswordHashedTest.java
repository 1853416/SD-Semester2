import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class isPasswordHashedTest {
    private isPasswordHashed subject;
    private Doctor doctor;
    private Patient patient;

    @Before
    public void setup() throws Exception {
        doctor = new Doctor("jackxnian@gmail.com","Dentist","Xin","Nian",
                "971202","Phd",69,"0824630844","9911290195083");
        patient = new Patient("test@user.co.za","Test123","Er","123131321321321",
                "0123456788 ","6655554462313");
        subject = new isPasswordHashed();
    }

    @Test
    public void testGetMessage() {
        String docHash = subject.hashPass(doctor.getPassword());
        assertTrue(subject.isHashed(docHash,doctor.getPassword()));

        assertFalse(subject.isHashed(patient.getPassword(),patient.getPassword()));
    }
}
