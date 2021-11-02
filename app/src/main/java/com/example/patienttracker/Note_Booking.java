package com.example.patienttracker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Note_Booking {

    private String date;
    private String doctor_documentID;
    private String patient_documentID;
    private Boolean doctor_isHalfHourSlot;
    private String timeSlot;
    private String dateOfAction;
    private Boolean missed;

    private String doctor_fullName;
    private String patient_fullName;

    private String time;

    private Date timestamp;

    public Note_Booking() {
        //Empty constructor needed
    }

    public Note_Booking(String date, String date_of_action, String time_slot , String doctor_documentID, String patient_documentID,
                        Boolean doctor_isHalfHourSlot,String doctor_fullName, String patient_fullName)
    {
        this.date = date;
        this.dateOfAction = date_of_action;
        this.timeSlot = time_slot;
        this.doctor_documentID = doctor_documentID;
        this.patient_documentID = patient_documentID;
        this.doctor_isHalfHourSlot = doctor_isHalfHourSlot;
        this.missed = false;

        this.doctor_fullName = doctor_fullName;
        this.patient_fullName = patient_fullName;

        this.time = timeSlotToTime(this.timeSlot,this.doctor_isHalfHourSlot);

        this.timestamp = getDateFromString(date + " " + time);
    }

    static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public Date getDateFromString(String Date){

        try {
            Date date = format.parse(Date);
            return date ;
        } catch (ParseException e){
            return null ;
        }

    }

    private String timeSlotToTime(String timeSlot,Boolean isHalfHourSlot) {
        String temp_time_string = "Error";

        if (isHalfHourSlot){
            switch (timeSlot){
                case "time01":
                    temp_time_string = "00:00";
                    break;
                case "time02":
                    temp_time_string = "00:30";
                    break;
                case "time03":
                    temp_time_string = "01:00";
                    break;
                case "time04":
                    temp_time_string = "01:30";
                    break;

                case "time05":
                    temp_time_string = "02:00";
                    break;
                case "time06":
                    temp_time_string = "02:30";
                    break;
                case "time07":
                    temp_time_string = "03:00";
                    break;
                case "time08":
                    temp_time_string = "03:30";
                    break;

                case "time09":
                    temp_time_string = "04:00";
                    break;
                case "time10":
                    temp_time_string = "04:30";
                    break;
                case "time11":
                    temp_time_string = "05:00";
                    break;
                case "time12":
                    temp_time_string = "05:30";
                    break;

                case "time13":
                    temp_time_string = "06:00";
                    break;
                case "time14":
                    temp_time_string = "06:30";
                    break;
                case "time15":
                    temp_time_string = "07:00";
                    break;
                case "time16":
                    temp_time_string = "07:30";
                    break;

                case "time17":
                    temp_time_string = "08:00";
                    break;
                case "time18":
                    temp_time_string = "08:30";
                    break;
                case "time19":
                    temp_time_string = "09:00";
                    break;
                case "time20":
                    temp_time_string = "09:30";
                    break;

                case "time21":
                    temp_time_string = "10:00";
                    break;
                case "time22":
                    temp_time_string = "10:30";
                    break;
                case "time23":
                    temp_time_string = "11:00";
                    break;
                case "time24":
                    temp_time_string = "11:30";
                    break;

                case "time25":
                    temp_time_string = "12:00";
                    break;
                case "time26":
                    temp_time_string = "12:30";
                    break;
                case "time27":
                    temp_time_string = "13:00";
                    break;
                case "time28":
                    temp_time_string = "13:30";
                    break;

                case "time29":
                    temp_time_string = "14:00";
                    break;
                case "time30":
                    temp_time_string = "14:30";
                    break;
                case "time31":
                    temp_time_string = "15:00";
                    break;
                case "time32":
                    temp_time_string = "15:30";
                    break;

                case "time33":
                    temp_time_string = "16:00";
                    break;
                case "time34":
                    temp_time_string = "16:30";
                    break;
                case "time35":
                    temp_time_string = "17:00";
                    break;
                case "time36":
                    temp_time_string = "17:30";
                    break;

                case "time37":
                    temp_time_string = "18:00";
                    break;
                case "time38":
                    temp_time_string = "18:30";
                    break;
                case "time39":
                    temp_time_string = "19:00";
                    break;
                case "time40":
                    temp_time_string = "19:30";
                    break;

                case "time41":
                    temp_time_string = "20:00";
                    break;
                case "time42":
                    temp_time_string = "20:30";
                    break;
                case "time43":
                    temp_time_string = "21:00";
                    break;
                case "time44":
                    temp_time_string = "21:30";
                    break;

                case "time45":
                    temp_time_string = "22:00";
                    break;
                case "time46":
                    temp_time_string = "22:30";
                    break;
                case "time47":
                    temp_time_string = "23:00";
                    break;
                case "time48":
                    temp_time_string = "23:30";
                    break;
            }

        }else {
            switch (timeSlot){
                case "time01":
                    temp_time_string = "00:00";
                    break;
                case "time02":
                    temp_time_string = "01:00";
                    break;
                case "time03":
                    temp_time_string = "02:00";
                    break;
                case "time04":
                    temp_time_string = "03:00";
                    break;

                case "time05":
                    temp_time_string = "04:00";
                    break;
                case "time06":
                    temp_time_string = "05:00";
                    break;
                case "time07":
                    temp_time_string = "06:00";
                    break;
                case "time08":
                    temp_time_string = "07:00";
                    break;

                case "time09":
                    temp_time_string = "08:00";
                    break;
                case "time10":
                    temp_time_string = "09:00";
                    break;
                case "time11":
                    temp_time_string = "10:00";
                    break;
                case "time12":
                    temp_time_string = "11:00";
                    break;

                case "time13":
                    temp_time_string = "12:00";
                    break;
                case "time14":
                    temp_time_string = "13:00";
                    break;
                case "time15":
                    temp_time_string = "14:00";
                    break;
                case "time16":
                    temp_time_string = "15:00";
                    break;

                case "time17":
                    temp_time_string = "16:00";
                    break;
                case "time18":
                    temp_time_string = "17:00";
                    break;
                case "time19":
                    temp_time_string = "18:00";
                    break;
                case "time20":
                    temp_time_string = "19:00";
                    break;

                case "time21":
                    temp_time_string = "20:00";
                    break;
                case "time22":
                    temp_time_string = "21:00";
                    break;
                case "time23":
                    temp_time_string = "22:00";
                    break;
                case "time24":
                    temp_time_string = "23:00";
                    break;
            }
        }

        return temp_time_string;
    }

    public String getDate() {
        return date;
    }

    public String getDateOfAction() {
        return dateOfAction;
    }

    public String getTimeSlot() { return timeSlot; }

    public String getDoctor_documentID() {
        return doctor_documentID;
    }

    public String getPatient_documentID() {
        return patient_documentID;
    }

    public Boolean getDoctor_isHalfHourSlot() {
        return doctor_isHalfHourSlot;
    }

    public Boolean getMissed() { return missed; }

    public String getTime() {
        return time;
    }

    public String getDoctor_fullName() {
        return doctor_fullName;
    }

    public String getPatient_fullName() {
        return patient_fullName;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
