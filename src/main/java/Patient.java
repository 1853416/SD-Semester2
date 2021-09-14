public class Patient {
    String email;
    String Name;
    String Surname;
    String Password;
    String phoneNum;
    String ID;

    public Patient(String email, String name, String surname, String password, String phoneNum, String ID) {
        this.email = email;
        Name = name;
        Surname = surname;
        Password = password;
        this.phoneNum = phoneNum;
        this.ID = ID;
    }

    public String getEmail() {
        return email;
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

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getID() {
        return ID;
    }
}
