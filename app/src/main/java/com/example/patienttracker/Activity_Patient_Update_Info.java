package com.example.patienttracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.Map;

public class Activity_Patient_Update_Info extends AppCompatActivity {
    //variables
    private String entered_password, patient_documentID;;
    private Map<String, Object> data ;
    private Note_Patient note_patient;
    private String userEmail,userFName,userLName,userID;

    //widgets
    private TextInputLayout il_email, il_first_name, il_last_name, il_id;
    private EditText et_email, et_first_name, et_last_name, et_id;
    private Button btn_update,btn_back;

    private Dialog dialog_successful;
    private Button btn_continue;

    //database
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionPatientReference = db.collection("Patient");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_update_info);

        Intent intent = getIntent();
        patient_documentID = intent.getStringExtra(Fragment_Patient_Home.phoneKey);

        il_email      = findViewById(R.id.IL_A_PatientUpdate_Email);
        il_first_name = findViewById(R.id.IL_A_PatientUpdate_FirstName);
        il_last_name  = findViewById(R.id.IL_A_PatientUpdate_LastName);
        il_id         = findViewById(R.id.IL_A_PatientUpdate_ID);

        et_email      = findViewById(R.id.ET_A_PatientUpdate_Email);
        et_first_name = findViewById(R.id.ET_A_PatientUpdate_FirstName);
        et_last_name  = findViewById(R.id.ET_A_PatientUpdate_LastName);
        et_id         = findViewById(R.id.ET_A_PatientUpdate_ID);

        et_email      .addTextChangedListener(emailTextWatcher);
        et_first_name .addTextChangedListener(firstnameTextWatcher);
        et_last_name  .addTextChangedListener(lastnameTextWatcher);
        et_id         .addTextChangedListener(idTextWatcher);

        btn_update = findViewById(R.id.B_A_PatientUpdate_Update);
        btn_back = findViewById(R.id.B_A_PatientUpdate_Back);

        dialog_successful = new Dialog(this);
        dialog_successful.setContentView(R.layout.dialog_successful_update);
        dialog_successful.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btn_continue = dialog_successful.findViewById(R.id.B_D_Success_Continue);
        
        getPatientData();
    }

    @Override
    protected void onStart() {
        super.onStart();

        btn_update.setOnClickListener(v -> {
            updatePatientData();
        });

        btn_back.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void updatePatientData()
    {
        collectionPatientReference
                .document(patient_documentID)
                .update("Email",userEmail,
                        "FirstName",userFName,
                        "LastName",userLName,
                        "ID",userID)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        openSuccessfulDialog();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        openFailDialog();
                    }
                });
    }

    //TextWatchers
    private TextWatcher emailTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            userEmail = et_email.getText().toString().trim();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            il_email.setError((null));
            btn_update.setEnabled(true);
        }
    };
    private TextWatcher firstnameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            userFName = et_first_name.getText().toString().trim();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            il_first_name.setError((null));
            btn_update.setEnabled(true);
        }
    };
    private TextWatcher lastnameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            userLName = et_last_name.getText().toString().trim();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            il_last_name.setError((null));
            btn_update.setEnabled(true);
        }
    };
    private TextWatcher idTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            userID = et_id.getText().toString().trim();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            il_id.setError((null));
            btn_update.setEnabled(true);
        }
    };

    private void getPatientData() {
        collectionPatientReference
                .document(patient_documentID)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        data = documentSnapshot.getData();
                        note_patient = documentSnapshot.toObject(Note_Patient.class);

                        userEmail = note_patient.getEmail();
                        userFName = note_patient.getFirstName();
                        userLName = note_patient.getLastName();
                        userID = note_patient.getID();

                        et_email.setText(userEmail);
                        et_first_name.setText(userFName);
                        et_last_name.setText(userLName);
                        et_id.setText(userID);
                    }
                });
    }

    private void openSuccessfulDialog() {
        dialog_successful.show();
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToPatientLogin();
            }
        });
    }
    private void openFailDialog() {

    }
    private void backToPatientPage() {
        Intent intent = new Intent(this, Activity_Patient.class);
        //passing values to user activity
        intent.putExtra(Activity_Patient_Login.patientDataKEY, (Serializable) data);
        intent.putExtra(Activity_Patient_Login.patientPhoneKEY,patient_documentID);
        startActivity(intent);
    }

    private void backToPatientLogin() {
        Intent intent = new Intent(this, Activity_Patient_Login.class);
        //passing values to user activity
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        backToPatientPage();
    }
}