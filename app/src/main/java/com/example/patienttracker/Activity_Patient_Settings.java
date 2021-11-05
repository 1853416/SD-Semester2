package com.example.patienttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class Activity_Patient_Settings extends AppCompatActivity {
    //strings
    String patient_document_id;
    //widgets
    Button btn_policy,btn_update,btn_password,btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_settings);

        Intent intent = getIntent();
        patient_document_id = intent.getStringExtra(Fragment_Patient_Home.phoneKey);

        btn_policy = findViewById(R.id.B_A_PatientSetting_Policy);
        btn_update = findViewById(R.id.B_A_PatientSetting_Update_Info);
        btn_password = findViewById(R.id.B_A_PatientSetting_Change_Password);
        btn_back = findViewById(R.id.B_A_PatientSetting_Back);

    }

    @Override
    protected void onStart() {
        super.onStart();
        btn_policy.setOnClickListener(v -> {
            Intent intent = new Intent(this, Activity_Patient_Policy.class);
            startActivity(intent);
        });

        btn_update.setOnClickListener(v -> {
            Intent intent = new Intent(this, Activity_Patient_Update_Info.class);
            intent.putExtra(Fragment_Patient_Home.phoneKey,patient_document_id);
            startActivity(intent);
//            Toast.makeText(getApplicationContext(),"Coming Soon",Toast.LENGTH_SHORT).show();
        });

        btn_password.setOnClickListener(v -> {
            Intent intent = new Intent(this, Activity_Patient_Update_Password.class);
            intent.putExtra(Fragment_Patient_Home.phoneKey,patient_document_id);
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