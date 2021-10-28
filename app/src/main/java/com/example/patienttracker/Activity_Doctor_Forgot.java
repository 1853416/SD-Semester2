package com.example.patienttracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Activity_Doctor_Forgot extends AppCompatActivity {

    //Variables
    private String email;

    //Widgets
    private TextInputLayout il_email;
    private EditText et_email;

    private Button btn_confirm;
    private Button btn_back;

    //Firestore database
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference doctorCollectionReference = db.collection("Doctor");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_forgot);

        il_email = findViewById(R.id.IL_A_DoctorForgot_Email);
        et_email = findViewById(R.id.ET_A_DoctorForgot_Email);
        et_email.addTextChangedListener(emailTextWatcher);

        btn_confirm = findViewById(R.id.B_A_DoctorForgot_Confirm);
        btn_back = findViewById(R.id.B_A_DoctorForgot_Back);
    }

    @Override
    protected void onStart() {
        super.onStart();

        btn_confirm.setOnClickListener(v -> {
            resetPassword();
        });

        btn_back.setOnClickListener(v -> {
            backToLogin();
        });
    }

    //TextWatchers
    private TextWatcher emailTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            email = et_email.getText().toString().trim();
        }
        @Override
        public void afterTextChanged(Editable editable) {
            il_email.setError((null));
            btn_confirm.setEnabled(true);
        }
    };

    private void resetPassword(){

    }
    private void backToLogin(){
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}