package com.example.patienttracker;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
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

public class Adapter_Appointment_History_Doctor extends FirestoreRecyclerAdapter<Note_Booking, Adapter_Appointment_History_Doctor.Holder_Note_Appointment_History_Doctor>{
    private  OnItemClickListener listener;
    String patientname, doctorname,sDate;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionPatientReference = db.collection("Patient");
    public Adapter_Appointment_History_Doctor(@NonNull FirestoreRecyclerOptions<Note_Booking> options) {
        super(options);
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onBindViewHolder(@NonNull Holder_Note_Appointment_History_Doctor holder, int position, @NonNull Note_Booking model) {
        String DateTime = model.getDate() + " " + model.getTime();
        if (model.getMissed()){
            holder.cv_card.setCardBackgroundColor(Color.GRAY);
            holder.ll_buttons.setVisibility(View.GONE);
            DateTime += " MISSED !";
        }
        holder.tv_dateTime.setText(DateTime);
        holder.tv_patientName.setText(model.getPatient_fullName());
        holder.tv_patientNumber.setText(model.getPatient_documentID());
        patientname = model.getPatient_fullName();
        doctorname = model.getDoctor_fullName();
        sDate = model.getDate() + " " + model.getTime();

    }

    @NonNull
    @Override
    public Holder_Note_Appointment_History_Doctor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_appointment_past_doctor,parent,false);
        return new Holder_Note_Appointment_History_Doctor(view);
    }

    public void missAppointment(int position){
        getSnapshots().getSnapshot(position).getReference().update("missed",true);
    }

    class Holder_Note_Appointment_History_Doctor extends RecyclerView.ViewHolder{
        TextView tv_dateTime;
        TextView tv_patientName;
        TextView tv_patientNumber;

        CardView cv_card;

        LinearLayout ll_buttons;
        Button btn_view,btn_miss;

        Dialog dialog_miss;
        Button btn_miss_confirm;
        Button btn_miss_back;

        public Holder_Note_Appointment_History_Doctor(@NonNull View itemView) {
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
                    if (position != RecyclerView.NO_POSITION && listener != null){
                        listener.onItemCLick(getSnapshots().getSnapshot(position),position);
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
    public interface OnItemClickListener{
        void onItemCLick(DocumentSnapshot documentSnapshot,int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
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
                    +patientname+"\n \n"
                    +"You have missed you appointment with Dr."+doctorname+" on "+ sDate
                    +"\n" + "Please reschedule your appointment as soon as possible \n \n"+
                    "Thank you for using NotDiscovery!";
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
