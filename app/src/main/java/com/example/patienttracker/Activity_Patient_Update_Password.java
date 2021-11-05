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

public class Activity_Patient_Update_Password extends AppCompatActivity {
    //variables
    private String entered_password, patient_documentID;;
    private Map<String, Object> data ;

    //widgets
    private TextInputLayout il_password;
    private EditText et_password;
    private Button btn_confirm,btn_back;

    private Dialog dialog_successful;
    private Button btn_continue;

    //database
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionPatientReference = db.collection("Patient");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_update_password);

        Intent intent = getIntent();
        patient_documentID = intent.getStringExtra(Fragment_Patient_Home.phoneKey);

        il_password = findViewById(R.id.IL_A_PatientChange_Password);
        et_password = findViewById(R.id.ET_A_PatientChange_Password);

        et_password.addTextChangedListener(passwordTextWatcher);

        btn_confirm = findViewById(R.id.B_A_PatientChange_Confirm);
        btn_back = findViewById(R.id.B_A_PatientChange_Back);

        dialog_successful = new Dialog(this);
        dialog_successful.setContentView(R.layout.dialog_successful_update);
        dialog_successful.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        btn_continue = dialog_successful.findViewById(R.id.B_D_Success_Continue);

        getPatientData();
    }

    @Override
    protected void onStart() {
        super.onStart();

        btn_confirm.setOnClickListener(v -> {
            if (entered_password != null)
            {
                updatePassword();
            }else {
                il_password.setError("empty");
                btn_confirm.setEnabled(false);
            }
        });

        btn_back.setOnClickListener(v ->
        {
            backToPatientPage();
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

    private void updatePassword()
    {
        collectionPatientReference
                .document(patient_documentID)
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

    private void getPatientData() {
        collectionPatientReference
                .document(patient_documentID)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        data = documentSnapshot.getData();
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