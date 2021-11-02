package com.example.patienttracker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Fragment_Patient_Upcoming extends Fragment {
    //Variables
    public static final String phoneKey = "phonenumber";
    private String patient_phone;

    //database
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionBookingReference = db.collection("Booking");

    //adapter
    private Adapter_Appointment_Upcoming_Patient adapter;

    //query
    private Query query;

    public Fragment_Patient_Upcoming() {
        // Required empty public constructor
    }


    public static Fragment_Patient_Upcoming newInstance(String  Phone) {
        Fragment_Patient_Upcoming fragment = new Fragment_Patient_Upcoming();
        Bundle args = new Bundle();
        args.putString(phoneKey,Phone);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            patient_phone = getArguments().getString(phoneKey);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_patient_upcoming, container, false);
        //for some reason firestore addes 2 hours so add 2 hours here to match the time
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY)+2);
        Date currentDateTime = calendar.getTime();
        query = collectionBookingReference
                .whereEqualTo("patient_documentID", patient_phone)
                .whereGreaterThan("timestamp",currentDateTime);

        FirestoreRecyclerOptions<Note_Booking> options
                = new FirestoreRecyclerOptions.Builder<Note_Booking>()
                .setQuery(query,Note_Booking.class)
                .build();
        adapter = new Adapter_Appointment_Upcoming_Patient(options);

        RecyclerView recyclerView = view.findViewById(R.id.RV_F_Patient_Upcoming);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}