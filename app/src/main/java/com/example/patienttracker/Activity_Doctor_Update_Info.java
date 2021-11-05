package com.example.patienttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Activity_Doctor_Update_Info extends AppCompatActivity {
    //variables
    public final static String doctorPhoneKEY = "DoctorPhone";
    private Note_Doctor note_doctor;

    //database
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionBookingReference = db.collection("Booking");
    private CollectionReference collectionDoctorReference = db.collection("Doctor");
    private CollectionReference collectionPatientReference = db.collection("Patient");
    String doctor_documentID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_update_info);

        final Intent intent = getIntent();
        doctor_documentID = intent.getStringExtra(Activity_Doctor_Login.doctorPhoneKEY);
    }
}