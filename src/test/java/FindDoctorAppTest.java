import org.junit.Assert;
import org.junit.Test;

public class FindDoctorAppTest {
    Appointment appointment = new Appointment();
    @Test
    public void findDoctor(){
        Assert.assertEquals(appointment.FindDoc("General Surgeon"),"Success!");
        Assert.assertEquals(appointment.FindDoc("Some Guy?"),"Fail!");
    }

    @Test
    public void canSetTimeAvail(){
        Assert.assertEquals(appointment.setAppointmentTimeAvail("08:30",true),"Success!");
    }

    @Test
    public void isAvailableTest(){
        Assert.assertEquals(appointment.setAppointmentTimeAvail("08:30",true),"Success!");
        Assert.assertTrue(appointment.docAvailable("08:30"));
        Assert.assertFalse(appointment.docAvailable("18:30"));
    }

    @Test
    public void makeApp(){
        Assert.assertEquals(appointment.setAppointmentTimeAvail("08:30",true),"Success!");
        Assert.assertEquals(appointment.saveFormDatabase("08:30","2021/11/29"),"Saved to Database");
        Assert.assertEquals(appointment.saveFormDatabase("18:30","2021/11/29"),"Failed to Save");
    }
}
