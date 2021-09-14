import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ValidNameTest {
    private ValidName subject;

    @Before
    public void setup() {
        subject = new ValidName();
    }

    @Test
    public void testGetMessage() {
        assertTrue(subject.isNameValid("Melisha"));
    }
}
