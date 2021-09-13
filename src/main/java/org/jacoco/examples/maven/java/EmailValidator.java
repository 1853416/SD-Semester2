package org.jacoco.examples.maven.java;

public class EmailValidator {
    public boolean isValidEmail(String email){
           return email.contains("@") && (email.contains(".com") || email.contains(".co.za"));
       }
}
