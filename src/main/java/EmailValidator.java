public class EmailValidator {
    public boolean isEmailValid(String email){
        return email.contains("@") && (email.contains(".com") || email.contains(".co.za"));
    }
}
