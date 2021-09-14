public class ValidIDNumber {
    public boolean isIDValid(String number){
        return number.length() == 13 && number.matches("[0-9]+");
    }
}
