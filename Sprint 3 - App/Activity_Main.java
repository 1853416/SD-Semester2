package com.example.patienttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_Doctor = (Button) findViewById(R.id.B_A_Main_Doctor);
        Button btn_Patient = (Button) findViewById(R.id.B_A_Main_Patient);

        btn_Doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDoctorLogin();
            }
        });
        btn_Patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPatientLogin();
            }
        });
    }

    //function calls to open the login page for doctors
    private void openDoctorLogin() {
        Intent intent = new Intent(this, Activity_Doctor_Login.class);
        startActivity(intent);
    }
    //function calls to open the login page for patients
    private void openPatientLogin() {
        Intent intent = new Intent(this, Activity_Patient_Login.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
//        if (){
//
//        }else {
//            super.onBackPressed();
//        }
    }//end of onBackPressed
}