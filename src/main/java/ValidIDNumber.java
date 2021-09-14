public class ValidIDNumber {
    public boolean isIDValid(String number){

        if(number.length() == 13 && number.matches("[0-9]+")){
            return true;
        }
        else{
            return false;
        }
    }
}
