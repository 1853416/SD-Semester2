package com.example.patienttracker;

import static android.content.ContentValues.TAG;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Adapter_Appointment_Upcomming_Doctor extends RecyclerView.Adapter<Adapter_Appointment_Upcomming_Doctor.AppointmentViewHolder> {
    private ArrayList<Note_Appointment> mAppList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference BookingCollectionRef = db.collection("Booking");

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_Appointment_Date;
        public TextView tv_Name;
        public TextView tv_Number;
        public Button btn_cancel;
        public Dialog dialog_delete;
        public Button btn_delete_confirm;
        public Button btn_delete_back;

        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_Appointment_Date = itemView.findViewById(R.id.TV_C_Appointment_AppDate);
            tv_Name = itemView.findViewById(R.id.TV_C_Appointment_Name);
            tv_Number = itemView.findViewById(R.id.TV_C_Appointment_Number);

            btn_cancel = itemView.findViewById(R.id.B_C_Appointment_Cancel);

            dialog_delete = new Dialog(itemView.getContext());
            dialog_delete.setContentView(R.layout.dialog_delete_appointment);
            dialog_delete.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            btn_delete_confirm = dialog_delete.findViewById(R.id.B_D_Delete_Confirm);
            btn_delete_back = dialog_delete.findViewById(R.id.B_D_Delete_Back);
        }
    }

    public Adapter_Appointment_Upcomming_Doctor(ArrayList<Note_Appointment> AppHistList){
        mAppList = AppHistList;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_appointment_upcomming_doctor,parent,false);
//        AppointmentViewHolder avh = new AppointmentViewHolder(v);
        return new AppointmentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        Note_Appointment current = mAppList.get(position);

        holder.tv_Appointment_Date.setText(current.getAppointmentDateTime());
        holder.tv_Name.setText(current.getName());
        holder.tv_Number.setText(current.getNumber());

        holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.dialog_delete.show();
                holder.btn_delete_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BookingCollectionRef.document(current.getDocumentID())
                                .delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                                        holder.dialog_delete.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "DocumentSnapshot successfully deleted!");
                                        Toast.makeText(v.getContext(), "Error!\n Please Try again later", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
                holder.btn_delete_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.dialog_delete.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAppList.size();
    }
}
