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

public class Adapter_Appointment_Upcoming_Patient extends FirestoreRecyclerAdapter<Note_Booking, Adapter_Appointment_Upcoming_Patient.Holder_Note_Booking_Upcoming_Patient>{
    String patientname, doctorname,sDate;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionDoctorReference = db.collection("Doctor");
    public Adapter_Appointment_Upcoming_Patient(@NonNull FirestoreRecyclerOptions<Note_Booking> options) {
        super(options);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull Holder_Note_Booking_Upcoming_Patient holder, int position, @NonNull Note_Booking model) {
        holder.tv_DateTime.setText(model.getDate() + " " + model.getTime());
        holder.tv_DoctorName.setText("Dr." + model.getDoctor_fullName());
        holder.tv_DoctorNumber.setText(model.getDoctor_documentID());
        patientname = model.getPatient_fullName();
        doctorname = model.getDoctor_fullName();
        sDate = model.getDate() + " " + model.getTime();
    }

    @NonNull
    @Override
    public Holder_Note_Booking_Upcoming_Patient onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_appointment_upcomming_patient,parent,false);
        return new Holder_Note_Booking_Upcoming_Patient(view);
    }

    public void deleteItem(int position)
    {
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    class Holder_Note_Booking_Upcoming_Patient extends RecyclerView.ViewHolder{
        TextView tv_DateTime;
        TextView tv_DoctorName;
        TextView tv_DoctorNumber;

        Button btn_cancel;

        Dialog dialog_delete;
        Button btn_delete_confirm;
        Button btn_delete_back;

        public Holder_Note_Booking_Upcoming_Patient(@NonNull View itemView) {
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
                            collectionDoctorReference.document((String) tv_DoctorNumber.getText())
                                    .get()
                                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                        @Override
                                        public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            String dEmail = (String) documentSnapshot.get("Email");
                                            new EmailTask(dEmail).execute();
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
        String doctorEmail;
        private EmailTask(String dEmail){
            doctorEmail = dEmail;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            final String Username = "notdiscoveryemails2@gmail.com";
            final String Password = "SDgroup12";
            String MessagetoSend = "Good Day, Dr."
                    +doctorname+"\n"
                    +"This is too inform you that your appointment with " + patientname+" on "+ sDate+ " has been cancelled"
                    +"\n" + "They will have to reschedule another appointment  \n \n" +"Thank you for using NotDiscovery!";
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
                String SendList = doctorEmail;
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
