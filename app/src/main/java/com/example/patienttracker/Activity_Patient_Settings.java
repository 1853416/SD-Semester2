package com.example.patienttracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Activity_Patient_Settings extends AppCompatActivity {
    private String patient_document_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_settings);

        Intent intent = getIntent();
        patient_document_id = intent.getStringExtra(Fragment_Patient_Home.phoneKey);
    }
}