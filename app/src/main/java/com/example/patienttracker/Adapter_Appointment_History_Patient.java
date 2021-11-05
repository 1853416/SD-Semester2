package com.example.patienttracker;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class Adapter_Appointment_History_Patient extends FirestoreRecyclerAdapter<Note_Booking, Adapter_Appointment_History_Patient.Holder_Note_Appointment_History_Patient>{
    private Adapter_Appointment_History_Doctor.OnItemClickListener listener;
    public Adapter_Appointment_History_Patient(@NonNull FirestoreRecyclerOptions<Note_Booking> options) {
        super(options);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull Holder_Note_Appointment_History_Patient holder, int position, @NonNull Note_Booking model) {
        String DateTime = model.getDate() + " " + model.getTime();
        if (model.getMissed()){
            holder.cv_card.setCardBackgroundColor(Color.GRAY);
            holder.btn_view.setVisibility(View.GONE);
            DateTime += " MISSED !";
        }
        holder.tv_DateTime.setText(DateTime);
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

        CardView cv_card;

        Button btn_view;

        public Holder_Note_Appointment_History_Patient(@NonNull View itemView) {
            super(itemView);
            tv_DateTime = itemView.findViewById(R.id.TV_C_Appointment_dateTime);
            tv_DoctorName = itemView.findViewById(R.id.TV_C_Appointment_patient_fullName);
            tv_DoctorNumber = itemView.findViewById(R.id.TV_C_Appointment_doctor_number);
            btn_view = itemView.findViewById(R.id.B_C_Appointment_View);

            cv_card = itemView.findViewById(R.id.card);

            btn_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null){
                        listener.onItemCLick(getSnapshots().getSnapshot(position),position);
                    }
                }
            });
        }
    }
    public interface OnItemClickListener{
        void onItemCLick(DocumentSnapshot documentSnapshot, int position);
    }

    public void setOnItemClickListener(Adapter_Appointment_History_Doctor.OnItemClickListener listener){
        this.listener = listener;
    }
}