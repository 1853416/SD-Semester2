package com.example.patienttracker;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class Adapter_Appointment_History_Patient extends FirestoreRecyclerAdapter<Note_Booking, Adapter_Appointment_History_Patient.Holder_Note_Appointment_History_Patient>{

    public Adapter_Appointment_History_Patient(@NonNull FirestoreRecyclerOptions<Note_Booking> options) {
        super(options);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull Holder_Note_Appointment_History_Patient holder, int position, @NonNull Note_Booking model) {
        holder.tv_DateTime.setText(model.getDate() + " " + model.getTime());
        holder.tv_DoctorName.setText("Dr." + model.getDoctor_fullName());
        holder.tv_DoctorNumber.setText(model.getDoctor_documentID());

    }

    @NonNull
    @Override
    public Holder_Note_Appointment_History_Patient onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_appointment_past_patient,parent,false);
        return new Holder_Note_Appointment_History_Patient(view);
    }

    class Holder_Note_Appointment_History_Patient extends RecyclerView.ViewHolder{
        TextView tv_DateTime;
        TextView tv_DoctorName;
        TextView tv_DoctorNumber;

        public Holder_Note_Appointment_History_Patient(@NonNull View itemView) {
            super(itemView);
            tv_DateTime = itemView.findViewById(R.id.TV_C_Appointment_dateTime);
            tv_DoctorName = itemView.findViewById(R.id.TV_C_Appointment_patient_fullName);
            tv_DoctorNumber = itemView.findViewById(R.id.TV_C_Appointment_doctor_number);

        }
    }

}
