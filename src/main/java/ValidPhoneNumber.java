public class ValidPhoneNumber {
    public boolean isPhoneNumValid(String number){
        return number.length() == 10 && number.matches("[0-9]+");
    }
}

