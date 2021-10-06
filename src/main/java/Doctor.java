import java.util.ArrayList;

public class Doctor {
    String email;
    String Field;
    String Name;
    String Surname;
    String Password;
    String Qualification;
    int YearsExp;
    String phoneNum;
    String ID;
    ArrayList<String> times = new ArrayList<>();
    ArrayList<Boolean> isAvailable = new ArrayList<>();

    public Doctor(String email, String field, String name, String surname, String password, String qualification, int yearsExp, String phoneNum, String ID) {
        this.email = email;
        Field = field;
        Name = name;
        Surname = surname;
        Password = password;
        Qualification = qualification;
        YearsExp = yearsExp;
        this.phoneNum = phoneNum;
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public String getField() {
        return Field;
    }

    public String getName() {
        return Name;
    }

    public String getSurname() {
        return Surname;
    }

    public String getPassword() {
        return Password;
    }

    public String getQualification() {
        return Qualification;
    }

    public int getYearsExp() {
        return YearsExp;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getID() {
        return ID;
    }

    public void setTimesAvailability(String time, boolean isAvail) {
        times.add(time);
        isAvailable.add(isAvail);
    }

    public boolean isAvail(String time){
        for(int i = 0; i < times.size(); ++i){
            if(times.get(i).equals(time)){
                return isAvailable.get(i);
            }
        }

        return false;
    }

}
