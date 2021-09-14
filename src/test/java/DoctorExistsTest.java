import org.junit.Before;
import org.junit.Test;

import javax.print.Doc;

import static org.junit.Assert.assertTrue;

public class DoctorExistsTest {
        private DoctorExists subject;

        @Before
        public void setup() {
            subject = new DoctorExists();
            subject.setDoctorsInDatabase("0784282031");
        }

        @Test
        public void testGetMessage() {
            assertTrue(subject.isDoctorValid("0784282031"));
        }
}
