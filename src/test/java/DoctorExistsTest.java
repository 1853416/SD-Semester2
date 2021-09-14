import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DoctorExistsTest {
        private DoctorExists subject;
        private Doctor doctor;
        @Before
        public void setup() {
            subject = new DoctorExists();
            doctor = new Doctor("jackxnian@gmail.com","Dentist","Xin","Nian",
                    "971202","Phd",69,"0824630844","9911290195083");
            subject.setDoctorsInDatabase(doctor);
        }

        @Test
        public void testGetMessage() {
            assertTrue(subject.isDoctorValid(doctor));
        }
}
