package com.example.patienttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.io.Serializable;

public class Activity_Doctor_Settings extends AppCompatActivity {
    //string
    private static final String phoneKey            = "phonenumber";
    String doctor_documentID;
    //widgets
    Button btn_policy,btn_update,btn_password,btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_settings);

        final Intent intent = getIntent();
        doctor_documentID = intent.getStringExtra(phoneKey);

        btn_policy = findViewById(R.id.B_A_DoctorSetting_Policy);
        btn_update = findViewById(R.id.B_A_DoctorSetting_Update_Info);
        btn_password = findViewById(R.id.B_A_DoctorSetting_Change_Password);
        btn_back = findViewById(R.id.B_A_DoctorSetting_Back);
    }

    @Override
    protected void onStart() {
        super.onStart();

        btn_policy.setOnClickListener(v -> {
            Intent intent = new Intent(this, Activity_Doctor_Policy.class);
            startActivity(intent);
        });

        btn_update.setOnClickListener(v -> {
//            Intent intent = new Intent(this, Activity_Doctor_Update_Info.class);
//            intent.putExtra(phoneKey,doctor_documentID);
//            startActivity(intent);
            Toast.makeText(getApplicationContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
        });

        btn_password.setOnClickListener(v -> {
            Intent intent = new Intent(this, Activity_Doctor_Update_Password.class);
            intent.putExtra(phoneKey,doctor_documentID);
            startActivity(intent);
        });

        btn_back.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}