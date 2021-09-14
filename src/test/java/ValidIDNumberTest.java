import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ValidIDNumberTest {
    private ValidIDNumber subject;

    @Before
    public void setup() {
        subject = new ValidIDNumber();
    }

    @Test
    public void testGetMessage() {
        assertTrue(subject.isIDValid("9911290195083"));
    }
}
