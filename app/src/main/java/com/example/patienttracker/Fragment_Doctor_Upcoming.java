package com.example.patienttracker;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.Calendar;
import java.util.Date;


public class Fragment_Doctor_Upcoming extends Fragment {

    //variables
    private static final String phoneKey            = "phonenumber";
    private String doctor_documentID;


    //database
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionBookingReference = db.collection("Booking");

    //adapter
    private Adapter_Appointment_Upcoming_Doctor adapter;

    //query
    private Query query;

    public Fragment_Doctor_Upcoming() {
        // Required empty public constructor
    }

    public static Fragment_Doctor_Upcoming newInstance(String Phone) {
        Fragment_Doctor_Upcoming fragment = new Fragment_Doctor_Upcoming();
        Bundle args = new Bundle();
        args.putString(phoneKey,Phone);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            doctor_documentID = getArguments().getString(phoneKey);
        }
    }//End of onCrete

    @Nullable
    @Override  //Cycle After onCrete
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctor_upcoming, container, false);
//for some reason firestore addes 2 hours so add 2 hours here to match the time
//        Calendar calendar=Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY)+2);
//        Date currentDateTime = calendar.getTime();
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
        Date currentDateTime = calendar.getTime();
        query = collectionBookingReference
                .whereEqualTo("doctor_documentID", doctor_documentID)
                .whereGreaterThan("timestamp",currentDateTime);

        FirestoreRecyclerOptions<Note_Booking> options
                = new FirestoreRecyclerOptions.Builder<Note_Booking>()
                .setQuery(query,Note_Booking.class)
                .build();
        adapter = new Adapter_Appointment_Upcoming_Doctor(options);

        RecyclerView recyclerView = view.findViewById(R.id.RV_F_Doctor_Upcoming);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }//end of onCreteView


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