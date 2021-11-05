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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
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
    private String doctor_documentID;
    private String
            userEmail = "" ,userFName = "",userLName = "",
            userID = "", userFields = "",userQualifications = "",userYears = "";

    private Map<String, Object> doctorData ;

    //Widgets
    private TextInputLayout il_email,il_first_name,il_last_name,il_id,il_qualifications,il_years;

    private EditText et_email, et_first_name, et_last_name, et_id, et_qualifications, et_years;

    private Button btn_update,btn_back;

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

        TextView tv_phone = findViewById(R.id.TV_DoctorUpdate_Phone);

        tv_phone.setText("Phone Number: "+doctor_documentID);

        il_email            = findViewById(R.id.IL_A_DoctorUpdate_Email);
        il_first_name       = findViewById(R.id.IL_A_DoctorUpdate_FirstName);
        il_last_name        = findViewById(R.id.IL_A_DoctorUpdate_LastName);
        il_id               = findViewById(R.id.IL_A_DoctorUpdate_ID);
        il_qualifications   = findViewById(R.id.IL_A_DoctorUpdate_Qualifications);
        il_years            = findViewById(R.id.IL_A_DoctorUpdate_Years);

        et_email            = findViewById(R.id.ET_A_DoctorUpdate_Email);
        et_first_name       = findViewById(R.id.ET_A_DoctorUpdate_FirstName);
        et_last_name        = findViewById(R.id.ET_A_DoctorUpdate_LastName);
        et_id               = findViewById(R.id.ET_A_DoctorUpdate_ID);
        et_qualifications   = findViewById(R.id.ET_A_DoctorUpdate_Qualifications);
        et_years            = findViewById(R.id.ET_A_DoctorUpdate_Years);

        et_email            .addTextChangedListener(emailTextWatcher);
        et_first_name       .addTextChangedListener(firstnameTextWatcher);
        et_last_name        .addTextChangedListener(lastnameTextWatcher);
        et_id               .addTextChangedListener(idTextWatcher);
        et_qualifications   .addTextChangedListener(qualificationsTextWatcher);
        et_years            .addTextChangedListener(yearsTextWatcher);

        btn_update = findViewById(R.id.B_A_DoctorUpdate_Update);
        btn_back = findViewById(R.id.B_A_DoctorUpdate_Back);

        dialog_successful = new Dialog(this);
        dialog_successful.setContentView(R.layout.dialog_successful_update);
        dialog_successful.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btn_continue = dialog_successful.findViewById(R.id.B_D_Success_Continue);

        getDoctorData();
    }

    @Override
    protected void onStart() {
        super.onStart();

        btn_update.setOnClickListener(v -> {
            updateDoctorData();
        });

        btn_back.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    private void updateDoctorData()
    {
        collectionDoctorReference
                .document(doctor_documentID)
                .update("Email",userEmail,
                        "FirstName",userFName,
                        "LastName",userLName,
                        "ID",userID,
                        "Qualifications",userQualifications,
                        "Years",userYears)
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
                        userEmail = note_doctor.getEmail();
                        userFName = note_doctor.getFirstName();
                        userLName = note_doctor.getLastName();
                        userID = note_doctor.getID();
                        userQualifications = note_doctor.getQualifications();
                        userYears = note_doctor.getYears();

                        et_email.setText(userEmail);
                        et_first_name.setText(userFName);
                        et_last_name.setText(userLName);
                        et_id.setText(userID);
                        et_qualifications.setText(userQualifications);
                        et_years.setText(userYears);
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
    private TextWatcher qualificationsTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            userQualifications = et_qualifications.getText().toString().trim();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            il_qualifications.setError((null));
            btn_update.setEnabled(true);
        }
    };
    private TextWatcher yearsTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            userYears = et_years.getText().toString().trim();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            il_years.setError((null));
            btn_update.setEnabled(true);
        }
    };


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