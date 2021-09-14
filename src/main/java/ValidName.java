public class ValidName {
    public boolean isNameValid(String name){
        return name.length() > 1 && name.matches("[a-zA-Z]+");
    }
}
