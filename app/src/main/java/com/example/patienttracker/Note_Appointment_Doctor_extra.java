package com.example.patienttracker;

public class Note_Appointment_Doctor_extra {
    private String appointmentDateTime;
    private String documentID;
    private String name;
    private String number;
    private String PateintEmail;

    public Note_Appointment_Doctor_extra(){
        //empty constructor
    }

    public Note_Appointment_Doctor_extra(String appointmentDate, String documentID, String name, String number,String PateintEmail){
        this.appointmentDateTime = appointmentDate;
        this.documentID = documentID;
        this.name = name;
        this.number = number;
        this.PateintEmail = PateintEmail;
    }

    public String getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public String getDocumentID() {
        return documentID;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getPateintEmail() {
        return PateintEmail;
    }
}



