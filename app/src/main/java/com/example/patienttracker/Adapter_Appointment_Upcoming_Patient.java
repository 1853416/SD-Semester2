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

public class Adapter_Appointment_Upcoming_Patient extends FirestoreRecyclerAdapter<Note_Booking, Adapter_Appointment_Upcoming_Patient.Holder_Note_Appointment>{

    public Adapter_Appointment_Upcoming_Patient(@NonNull FirestoreRecyclerOptions<Note_Booking> options) {
        super(options);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull Holder_Note_Appointment holder, int position, @NonNull Note_Booking model) {
        holder.tv_DateTime.setText(model.getDate() + " " + model.getTime());
        holder.tv_DoctorName.setText("Dr." + model.getDoctor_fullName());
        holder.tv_DoctorNumber.setText(model.getDoctor_documentID());

    }

    @NonNull
    @Override
    public Holder_Note_Appointment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_appointment_upcomming_patient,parent,false);
        return new Holder_Note_Appointment(view);
    }

    public void deleteItem(int position)
    {
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    class Holder_Note_Appointment extends RecyclerView.ViewHolder{
        TextView tv_DateTime;
        TextView tv_DoctorName;
        TextView tv_DoctorNumber;

        Button btn_cancel;

        Dialog dialog_delete;
        Button btn_delete_confirm;
        Button btn_delete_back;

        public Holder_Note_Appointment(@NonNull View itemView) {
            super(itemView);
            tv_DateTime = itemView.findViewById(R.id.TV_C_Appointment_dateTime);
            tv_DoctorName = itemView.findViewById(R.id.TV_C_Appointment_doctor_fullName);
            tv_DoctorNumber = itemView.findViewById(R.id.TV_C_Appointment_doctor_number);
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
