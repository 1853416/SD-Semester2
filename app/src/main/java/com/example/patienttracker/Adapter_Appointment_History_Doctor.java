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

public class Adapter_Appointment_History_Doctor extends FirestoreRecyclerAdapter<Note_Booking, Adapter_Appointment_History_Doctor.Holder_Note_Appointment>{

    public Adapter_Appointment_History_Doctor(@NonNull FirestoreRecyclerOptions<Note_Booking> options) {
        super(options);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull Adapter_Appointment_History_Doctor.Holder_Note_Appointment holder, int position, @NonNull Note_Booking model) {
        holder.tv_dateTime.setText(model.getDate() + " " + model.getTime());
        holder.tv_patientName.setText(model.getPatient_fullName());
        holder.tv_patientNumber.setText(model.getPatient_documentID());

    }

    @NonNull
    @Override
    public Adapter_Appointment_History_Doctor.Holder_Note_Appointment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_appointment_past_doctor,parent,false);
        return new Adapter_Appointment_History_Doctor.Holder_Note_Appointment(view);
    }

    class Holder_Note_Appointment extends RecyclerView.ViewHolder{
        TextView tv_dateTime;
        TextView tv_patientName;
        TextView tv_patientNumber;

        public Holder_Note_Appointment(@NonNull View itemView) {
            super(itemView);
            tv_dateTime = itemView.findViewById(R.id.TV_C_Appointment_dateTime);
            tv_patientName = itemView.findViewById(R.id.TV_C_Appointment_patient_fullName);
            tv_patientNumber = itemView.findViewById(R.id.TV_C_Appointment_doctor_number);

        }
    }

}
