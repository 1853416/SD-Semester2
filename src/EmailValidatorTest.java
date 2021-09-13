import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.*;
class EmailValidatorTest {

	@Test
	void test() {
		assertTrue(isValidEmail("name@email.com"));
	}
	
	public static boolean isValidEmail(String email){
        return email.contains("@") && (email.contains(".com") || email.contains(".co.za"));
    }

}
