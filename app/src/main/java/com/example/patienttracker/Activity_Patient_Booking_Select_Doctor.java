package com.example.patienttracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Activity_Patient_Booking_Select_Doctor extends AppCompatActivity {

    private static final String TAG = "Activity Patient Booking";

    //variables
    public ArrayList<String> doctorNames = new ArrayList<String>();
    public static String doctorInformationKey = "DoctorInformation";
    public static String doctorEmailKey = "DoctorEmail";
    public static String doctorNameKey = "DoctorName";
    private String doctorFields,doctorName, patient_document_id,patient_document_email,patient_first_Name,Patient_last_Name ;

    private Map<String, Object> patientData = new HashMap<>();

    //widgets
    private AutoCompleteTextView atv_Fields,atv_Doctors;
    private RecyclerView rv_doctors;
    private TextInputLayout il_Doctor_Names;
    private Button btn_back;

    //databse
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionDoctorReference = db.collection("Doctor");
    private CollectionReference collectionPatientReference = db.collection("Patient");

    //query
    private Query query;

    //adapter
    private Adapter_Doctor adapter_doctor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_booking_select_doctor);

        //get variables from previous activity
        Intent intent = getIntent();
        patient_document_id = intent.getStringExtra(Fragment_Patient_Home.phoneKey);
        patient_document_email = intent.getStringExtra(Fragment_Patient_Home.emailKey);
        patient_first_Name = intent.getStringExtra(Fragment_Patient_Home.firstNameKey);
        Patient_last_Name = intent.getStringExtra(Fragment_Patient_Home.lastNameKey);
        atv_Fields  = findViewById(R.id.ATV_A_PatientBooking_Fields);
        atv_Doctors = findViewById(R.id.ATV_A_PatientBooking_Doctors);
        il_Doctor_Names = findViewById(R.id.IL_A_PatientBooking_Doctors);
        rv_doctors = findViewById(R.id.RV_A_PatientBooking_Doctors);
        btn_back = findViewById(R.id.B_A_PatientBooking_Back);

        getPatientData();
        getDoctorNames();
    }

    @Override
    protected void onStart() {
        super.onStart();

        ArrayAdapter arrayAdapterDoctorFields =
                new ArrayAdapter(this,
                        R.layout.dropdown_items_doctor_feilds,
                        Activity_Doctor_Register.doctor_Fields);
        atv_Fields.setAdapter(arrayAdapterDoctorFields);

        ArrayAdapter arrayAdapterDoctorNames =
                new ArrayAdapter(this,
                        R.layout.dropdown_items_doctor_feilds,
                        doctorNames);
        atv_Doctors.setAdapter(arrayAdapterDoctorNames);

        atv_Fields.addTextChangedListener(fieldsTextWatcher);
        atv_Doctors.addTextChangedListener(namesTextWatcher);

        btn_back.setOnClickListener(view -> {onBackPressed();});
    }

    @Override
    protected void onStop() {
        super.onStop();
//        adapter_note_doctor_info.stopListening();
    }

    private void getPatientData(){
        collectionPatientReference.document(patient_document_id)
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        patientData = documentSnapshot.getData();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }

    private void getDoctorNames(){
        collectionDoctorReference.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())){
//                                Log.d(TAG, document.getId() + " => " + document.getData());
                                String tempname = (String) document.getData().get("FirstName");
                                doctorNames.add(tempname);
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void getDoctors(){
        FirestoreRecyclerOptions<Note_Doctor> options
                = new FirestoreRecyclerOptions.Builder<Note_Doctor>()
                .setQuery(query, Note_Doctor.class)
                .build();

        adapter_doctor = new Adapter_Doctor(options);

        rv_doctors.setLayoutManager(new LinearLayoutManager(this));
        rv_doctors.setAdapter(adapter_doctor);
    }

    private final TextWatcher fieldsTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            doctorFields = atv_Fields.getText().toString().trim();

        }

        @Override
        public void afterTextChanged(Editable editable) {
            query = collectionDoctorReference
                    .whereEqualTo("Fields",doctorFields)
                    .orderBy("FirstName",Query.Direction.ASCENDING);

            getDoctors();
            adapterStart();

            if (doctorFields != null){
//                il_Doctor_Names.setVisibility(View.VISIBLE);
            }
        }
    };

    private final TextWatcher namesTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            doctorName = atv_Doctors.getText().toString().trim();
        }

        @Override
        public void afterTextChanged(Editable editable) {
            Log.d(TAG, "afterTextChanged: " + doctorName);
            query = collectionDoctorReference
                    .whereEqualTo("Fields",doctorFields)
                    .whereEqualTo("FirstName",doctorName)
                    .orderBy("FirstName",Query.Direction.ASCENDING);

            getDoctors();
            adapterStart();
        }
    };

    private void adapterStart(){
        adapter_doctor.startListening();
        adapter_doctor
                .setOnItemClickListener(new Adapter_Doctor.OnItemClickListener() {
                    @Override
                    public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                        String doctor_fullName = documentSnapshot.toObject(Note_Doctor.class).getFirstName()+" "+documentSnapshot.toObject(Note_Doctor.class).getLastName();
                        selectTime(documentSnapshot.getId(),documentSnapshot.toObject(Note_Doctor.class).getEmail(),doctor_fullName);
                    }
                });
    }

    private void selectTime(String doctor_document_id, String doctor_document_email, String doctor_fullName) {
        Intent intent = new Intent(this, Activity_Patient_Booking_Select_Time.class);
        intent.putExtra(doctorInformationKey, doctor_document_id);
        intent.putExtra(doctorEmailKey, doctor_document_email);
        intent.putExtra(doctorNameKey,doctor_fullName);
        intent.putExtra(Fragment_Patient_Home.phoneKey, patient_document_id);
        intent.putExtra(Fragment_Patient_Home.emailKey, patient_document_email);
        intent.putExtra(Fragment_Patient_Home.firstNameKey, patient_first_Name);
        intent.putExtra(Fragment_Patient_Home.lastNameKey, Patient_last_Name);
        startActivity(intent);
        atv_Fields.setText("");
        atv_Doctors.setText("");
    }

    @Override
    public void onBackPressed() {
        backToPatientHome();
    }

    private void backToPatientHome(){
        Intent intent = new Intent(this, Activity_Patient.class);
        //passing values to user activity
        intent.putExtra(Activity_Patient_Login.patientDataKEY, (Serializable) patientData);
        intent.putExtra(Activity_Patient_Login.patientPhoneKEY,patient_document_id);
        startActivity(intent);
    }
}