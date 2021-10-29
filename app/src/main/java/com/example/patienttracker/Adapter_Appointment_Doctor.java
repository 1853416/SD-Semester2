package com.example.patienttracker;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Adapter_Appointment_Doctor extends RecyclerView.Adapter<Adapter_Appointment_Doctor.AppointmentViewHolder> {
    private ArrayList<Appointment_Doctor_extra> mAppList;
    private String patientName, AppDate,patientEmail;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionPatientReference = db.collection("Patient");
    public static class AppointmentViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_Appointment_Date;
        public TextView tv_Name;
        public TextView tv_Number;
        public Button btn_view;
        public Button btn_missed;
        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
                tv_Appointment_Date = itemView.findViewById(R.id.TV_C_Appointment_AppDate);
                tv_Name = itemView.findViewById(R.id.TV_C_Appointment_Name);
                tv_Number = itemView.findViewById(R.id.TV_C_Appointment_Number);
                btn_view = itemView.findViewById(R.id.B_C_Appointment_View);
                btn_missed = itemView.findViewById(R.id.B_C_Missed_Appointment);
        }
    }

    public Adapter_Appointment_Doctor(ArrayList<Appointment_Doctor_extra> AppHistList){
        mAppList = AppHistList;
    }

    @NonNull
    @Override
    public AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pastappointment,parent,false);
//        AppointmentViewHolder avh = new AppointmentViewHolder(v);
        return new AppointmentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {
        Appointment_Doctor_extra current = mAppList.get(position);

        holder.tv_Appointment_Date.setText(current.getAppointmentDateTime());
        holder.tv_Name.setText(current.getName());
        patientName = current.getName();
        AppDate = current.getAppointmentDateTime();
        patientEmail = current.getPateintEmail();
        holder.tv_Number.setText(current.getNumber());
        holder.btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(v.getContext(), current.getDocumentID(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), Activity_Doctor_SetAppointment.class);
                intent.putExtra("Appointment_Document_ID",current.getDocumentID());
                intent.putExtra("Appointment_Date_Time",current.getAppointmentDateTime());
                v.getContext().startActivity(intent);
            }
        });
        holder.btn_missed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new EmailTask().execute();
                CardView cView = holder.itemView.findViewById(R.id.card);
                cView.setCardBackgroundColor(Color.GRAY);
                holder.btn_missed.setEnabled(false);
                holder.btn_view.setEnabled(false);
                holder.btn_missed.setVisibility(View.GONE);
                holder.btn_view.setVisibility(View.GONE);
                //Toast.makeText(view.getContext(), "Missed Appointment", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAppList.size();
    }

    private class EmailTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            final String Username = "notdiscoveryemails2@gmail.com";
            final String Password = "SDgroup12";
            String MessagetoSend = "Hi, " + patientName+"\n" +"You missed your appointment with Dr." + "at "+ AppDate ;
            Properties props = new Properties();
            props.put("mail.smtp.auth","true");
            props.put("mail.smtp.starttls.enable","true");
            props.put("mail.smtp.host","smtp.gmail.com");
            props.put("mail.smtp.port","587");

            Session session = Session.getInstance(props,
                    new Authenticator(){
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication(){
                            return new PasswordAuthentication(Username,Password);
                        }
                    });

            try{
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(Username));
                //Log.d(TAG, "Patient Email in email task"+ patient_document_email);
                String SendList = patientEmail;
                message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(SendList) );
                message.setSubject("Missed Appointment");
                message.setText(MessagetoSend);
                Transport.send(message);
            }catch(MessagingException e){
                throw new RuntimeException(e);
            }

            return null;
        }
    }
}
