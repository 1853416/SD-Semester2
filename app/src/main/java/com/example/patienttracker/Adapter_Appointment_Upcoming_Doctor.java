package com.example.patienttracker;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Adapter_Appointment_Upcoming_Doctor extends FirestoreRecyclerAdapter<Note_Booking, Adapter_Appointment_Upcoming_Doctor.Holder_Note_Booking_Upcoming_Doctor>{

    String patientname, doctorname,sDate;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionPatientReference = db.collection("Patient");
    public Adapter_Appointment_Upcoming_Doctor(@NonNull FirestoreRecyclerOptions<Note_Booking> options) {
        super(options);

    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull Holder_Note_Booking_Upcoming_Doctor holder, int position, @NonNull Note_Booking model) {
        holder.tv_DateTime.setText(model.getDate() + " " + model.getTime());
        holder.tv_patientName.setText(model.getPatient_fullName());
        holder.tv_patientNumber.setText(model.getPatient_documentID());
        patientname = model.getPatient_fullName();
        doctorname = model.getDoctor_fullName();
        sDate = model.getDate() + " " + model.getTime();

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
                            collectionPatientReference.document((String) tv_patientNumber.getText())
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            String pEmail = (String) documentSnapshot.get("Email");
                                            new EmailTask(pEmail).execute();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                        }
                                    });
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

    private class EmailTask extends AsyncTask<Void,Void,Void> {
        String patientEmail;
        private EmailTask(String pEmail){
            patientEmail = pEmail;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            final String Username = "notdiscoveryemails2@gmail.com";
            final String Password = "SDgroup12";
            String MessagetoSend = "Good Day,"
                    +patientname+"\n"
                    +"This is too inform you that your appointment with  Dr." + doctorname+" on "+ sDate+ " has been cancelled"
                    +"\n" + " Please reschedule another appointment with you doctor";
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
                message.setSubject("Not Discovery Missed Appointment");
                message.setText(MessagetoSend);
                Transport.send(message);
            }catch(MessagingException e){
                throw new RuntimeException(e);
            }

            return null;
        }
    }

}
