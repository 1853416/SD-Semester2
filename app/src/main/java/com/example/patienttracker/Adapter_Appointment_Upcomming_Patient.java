package com.example.patienttracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_Appointment_Upcomming_Patient extends RecyclerView.Adapter<Adapter_Appointment_Upcomming_Patient.AppointmentViewHolder> {
    private  ArrayList<Note_Appointment> mAppList;

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_Appointment_Date;
        public TextView tv_Name;
        public TextView tv_Number;
        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Appointment_Date = itemView.findViewById(R.id.TV_C_Appointment_AppDate);
            tv_Name = itemView.findViewById(R.id.TV_C_Appointment_Name);
            tv_Number = itemView.findViewById(R.id.TV_C_Appointment_Number);
        }
    }

    public Adapter_Appointment_Upcomming_Patient(ArrayList<Note_Appointment> AppHistList){
        mAppList = AppHistList;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_appointment_upcomming,parent,false);
//        AppointmentViewHolder avh = new AppointmentViewHolder(v);
        return new AppointmentViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        Note_Appointment current = mAppList.get(position);
        holder.tv_Appointment_Date.setText(current.getAppointmentDateTime());
        holder.tv_Name.setText(current.getName());
        holder.tv_Number.setText(current.getNumber());
    }

    @Override
    public int getItemCount() {
        return mAppList.size();
    }
}
