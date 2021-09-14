import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmailValidatorTest {
    private EmailValidator subject;

    @Before
    public void setup() {
        subject = new EmailValidator();
    }
    @Test
    public void testGetMessage() {
        assertTrue(subject.isEmailValid("name@example.com"));
    }
}
