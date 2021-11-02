package com.example.patienttracker;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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
        if (model.getMissed()){
            holder.ll_buttons.setVisibility(View.GONE);
            holder.cv_card.setCardBackgroundColor(Color.GRAY);
        }
    }

    @NonNull
    @Override
    public Adapter_Appointment_History_Doctor.Holder_Note_Appointment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_appointment_past_doctor,parent,false);
        return new Adapter_Appointment_History_Doctor.Holder_Note_Appointment(view);
    }

    public void missAppointment(int position){
        getSnapshots().getSnapshot(position).getReference().update("missed",true);
    }

    class Holder_Note_Appointment extends RecyclerView.ViewHolder{
        TextView tv_dateTime;
        TextView tv_patientName;
        TextView tv_patientNumber;

        CardView cv_card;

        LinearLayout ll_buttons;
        Button btn_view,btn_miss;

        Dialog dialog_miss;
        Button btn_miss_confirm;
        Button btn_miss_back;

        public Holder_Note_Appointment(@NonNull View itemView) {
            super(itemView);
            tv_dateTime = itemView.findViewById(R.id.TV_C_Appointment_dateTime);
            tv_patientName = itemView.findViewById(R.id.TV_C_Appointment_patient_fullName);
            tv_patientNumber = itemView.findViewById(R.id.TV_C_Appointment_doctor_number);

            cv_card = itemView.findViewById(R.id.card);

            ll_buttons = itemView.findViewById(R.id.LL_C_Appointment_Buttons);
            btn_view = itemView.findViewById(R.id.B_C_Appointment_View);
            btn_miss = itemView.findViewById(R.id.B_C_Appointment_Missed);

            dialog_miss = new Dialog(itemView.getContext());
            dialog_miss.setContentView(R.layout.dialog_miss_appointment);
            dialog_miss.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            btn_miss_confirm = dialog_miss.findViewById(R.id.B_D_Missed_Confirm);
            btn_miss_back = dialog_miss.findViewById(R.id.B_D_Missed_Back);

            btn_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        String temp = getSnapshots().getSnapshot(position).getId();

                    }
                }
            });

            btn_miss.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog_miss.show();
                    btn_miss_confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            missAppointment(getLayoutPosition());
                            dialog_miss.dismiss();
                        }
                    });
                    btn_miss_back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog_miss.dismiss();
                        }
                    });
                }
            });
        }
    }

}
