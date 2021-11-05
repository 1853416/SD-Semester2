package com.example.patienttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.Map;

public class Activity_Doctor_Update_Info extends AppCompatActivity {
    //variables
    private static final String phoneKey = "phonenumber";
    private Note_Doctor note_doctor;
    private String entered_password, doctor_documentID;;

    private Map<String, Object> doctorData ;

    //widgets
    private TextInputLayout il_password;
    private EditText et_password;
    private Button btn_confirm,btn_back;

    private Dialog dialog_successful;
    private Button btn_continue;

    //database
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionDoctorReference = db.collection("Doctor");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_update_info);

        Intent intent = getIntent();
        doctor_documentID = intent.getStringExtra(phoneKey);

        dialog_successful = new Dialog(this);
        dialog_successful.setContentView(R.layout.dialog_successful_update);
        dialog_successful.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btn_continue = dialog_successful.findViewById(R.id.B_D_Success_Continue);

        getDoctorData();
    }

    private void getDoctorData() {
        collectionDoctorReference
                .document(doctor_documentID)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        note_doctor = documentSnapshot.toObject(Note_Doctor.class);
                        doctorData = documentSnapshot.getData();
                    }
                });
    }

    private void openSuccessfulDialog() {
        dialog_successful.show();
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToDoctorLogin();
            }
        });
    }
    private void openFailDialog() {

    }
    private void backToDoctorPage() {
        Intent intent = new Intent(this, Activity_Doctor.class);
        //passing values to user activity
        intent.putExtra(Activity_Doctor_Login.doctorDataKEY, (Serializable) doctorData);
        intent.putExtra(Activity_Doctor_Login.doctorPhoneKEY,doctor_documentID);
        startActivity(intent);
    }

    private void backToDoctorLogin() {
        Intent intent = new Intent(this, Activity_Doctor_Login.class);
        //passing values to user activity
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        backToDoctorPage();
    }
}