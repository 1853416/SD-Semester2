package com.example.patienttracker;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import java.util.Calendar;
import java.util.Date;

public class Fragment_Doctor_History extends Fragment {

    //variables
    private static final String TAG                 = "FragmentDoctorHome";
    private static final String phoneKey            = "phonenumber";
    private String doctor_documentID;

    //database
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionBookingReference = db.collection("Booking");

    //adapter
    private Adapter_Appointment_History_Doctor adapter;

    //query
    private Query query;


    public Fragment_Doctor_History() {
        // Required empty public constructor
    }

    public static Fragment_Doctor_History newInstance(String Phone) {
        Fragment_Doctor_History fragment = new Fragment_Doctor_History();
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctor_history, container, false);
//for some reason firestore addes 2 hours so add 2 hours here to match the time
//        Calendar calendar=Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY)+2);
//        Date currentDateTime = calendar.getTime();
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
        Date currentDateTime = calendar.getTime();
        query = collectionBookingReference
                .whereEqualTo("doctor_documentID", doctor_documentID)
                .whereLessThan("timestamp",currentDateTime);

        FirestoreRecyclerOptions<Note_Booking> options
                = new FirestoreRecyclerOptions.Builder<Note_Booking>()
                .setQuery(query,Note_Booking.class)
                .build();
        adapter = new Adapter_Appointment_History_Doctor(options);

        RecyclerView recyclerView = view.findViewById(R.id.RV_F_Doctor_History);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new Adapter_Appointment_History_Doctor.OnItemClickListener() {
            @Override
            public void onItemCLick(DocumentSnapshot documentSnapshot, int position) {
                Intent intent = new Intent(getContext(),Activity_Doctor_SetAppointment.class);
                intent.putExtra("Appointment_Document_ID", documentSnapshot.getId());
                startActivity(intent);
            }
        });

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