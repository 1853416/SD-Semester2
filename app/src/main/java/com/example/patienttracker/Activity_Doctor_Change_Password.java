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

public class Activity_Doctor_Change_Password extends AppCompatActivity {
    //variables
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
    private CollectionReference collectionBookingReference = db.collection("Booking");
    private CollectionReference collectionDoctorReference = db.collection("Doctor");
    private CollectionReference collectionPatientReference = db.collection("Patient");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_change_password);

        final Intent intent = getIntent();
        doctor_documentID = intent.getStringExtra(Activity_Doctor_Login.doctorPhoneKEY);

        il_password = findViewById(R.id.IL_A_DoctorChange_Password);
        et_password = findViewById(R.id.ET_A_DoctorChange_Password);

        et_password.addTextChangedListener(passwordTextWatcher);

        btn_confirm = findViewById(R.id.B_A_DoctorChange_Confirm);
        btn_back = findViewById(R.id.B_A_DoctorChange_Back);

        dialog_successful = new Dialog(this);
        dialog_successful.setContentView(R.layout.dialog_successful_update);
        dialog_successful.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btn_continue = dialog_successful.findViewById(R.id.B_D_Success_Continue);

        getDoctorData();
    }

    @Override
    protected void onStart() {
        super.onStart();

        btn_confirm.setOnClickListener(v -> {
            if (!entered_password.isEmpty())
            {
                updateDoctorPassword();
            }else
            {
                il_password.setError("empty");
                btn_confirm.setEnabled(true);
            }
        });

        btn_back.setOnClickListener(v ->
        {
            backToDoctorPage();
        });
    }

    private TextWatcher passwordTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            entered_password = et_password.getText().toString().trim();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            il_password.setError((null));
            btn_confirm.setEnabled(true);
        }
    };

    private void updateDoctorPassword()
    {
        collectionDoctorReference
                .document(doctor_documentID)
                .update("Password",entered_password)
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
                    }
                });
    }

    private void openSuccessfulDialog() {
        dialog_successful.show();
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backToDoctorPage();
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

    @Override
    public void onBackPressed() {
        backToDoctorPage();
    }
}