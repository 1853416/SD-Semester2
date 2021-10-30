import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AppointmentTest {

    @Test
    public void makeApp() {
        Appointments app = new Appointments();
        Assert.assertTrue( app.makeAppointment("pineapple","pen"));
    }

    @Test
    public void removeApp() {
        Appointments app = new Appointments();
        Assert.assertTrue( app.removeAppointment("pineapple"));
    }

}
