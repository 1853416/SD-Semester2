package com.example.patienttracker;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class Adapter_Appointment_Upcoming_Doctor extends FirestoreRecyclerAdapter<Note_Booking, Adapter_Appointment_Upcoming_Doctor.Holder_Note_Booking_Upcoming_Doctor>{

    public Adapter_Appointment_Upcoming_Doctor(@NonNull FirestoreRecyclerOptions<Note_Booking> options) {
        super(options);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull Holder_Note_Booking_Upcoming_Doctor holder, int position, @NonNull Note_Booking model) {
        holder.tv_DateTime.setText(model.getDate() + " " + model.getTime());
        holder.tv_patientName.setText(model.getPatient_fullName());
        holder.tv_patientNumber.setText(model.getPatient_documentID());

    }

    @NonNull
    @Override
    public Holder_Note_Booking_Upcoming_Doctor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_appointment_upcomming_doctor,parent,false);
        return new Holder_Note_Booking_Upcoming_Doctor(view);
    }

    public void deleteItem(int position)
    {
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    class Holder_Note_Booking_Upcoming_Doctor extends RecyclerView.ViewHolder{
        TextView tv_DateTime;
        TextView tv_patientName;
        TextView tv_patientNumber;

        Button btn_cancel;

        Dialog dialog_delete;
        Button btn_delete_confirm;
        Button btn_delete_back;

        public Holder_Note_Booking_Upcoming_Doctor(@NonNull View itemView) {
            super(itemView);
            tv_DateTime = itemView.findViewById(R.id.TV_C_Appointment_dateTime);
            tv_patientName = itemView.findViewById(R.id.TV_C_Appointment_patient_fullName);
            tv_patientNumber = itemView.findViewById(R.id.TV_C_Appointment_patient_number);
            btn_cancel = itemView.findViewById(R.id.B_C_Appointment_Cancel);

            dialog_delete = new Dialog(itemView.getContext());
            dialog_delete.setContentView(R.layout.dialog_delete_appointment);
            dialog_delete.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            btn_delete_confirm = dialog_delete.findViewById(R.id.B_D_Delete_Confirm);
            btn_delete_back = dialog_delete.findViewById(R.id.B_D_Delete_Back);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btn_cancel.getVisibility() == View.GONE){
                        btn_cancel.setVisibility(View.VISIBLE);
                    }else {
                        btn_cancel.setVisibility(View.GONE);
                    }
                }
            });

            btn_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog_delete.show();
                    btn_delete_confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            deleteItem(getLayoutPosition());
                            dialog_delete.dismiss();
                        }
                    });
                    btn_delete_back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog_delete.dismiss();
                        }
                    });
                }
            });
        }
    }

}
