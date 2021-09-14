import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ValidPhoneNumberTest {
    private ValidPhoneNumber subject;

    @Before
    public void setup() {
        subject = new ValidPhoneNumber();
    }

    @Test
    public void testGetMessage() {
        assertTrue(subject.isPhoneNumValid("0784282031"));
    }
}
