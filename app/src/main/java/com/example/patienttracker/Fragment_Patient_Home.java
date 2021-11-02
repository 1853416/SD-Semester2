package com.example.patienttracker;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Fragment_Patient_Home extends Fragment {

    //variables
    private static final String TAG                 = "Fragment_Patient_Home";

    public static final String firstNameKey        = "firstname";
    public static final String lastNameKey         = "lastname";
    public static final String phoneKey            = "phonenumber";
    public static final String emailKey            = "emailaddress";

    public static final String dateFormatPatten = "yyyy-MM-dd";

    private String
            patient_first_name, patient_last_name, patient_phone, patient_email,
            date_today;
    private final Date dateToday = new Date();
    private QueryDocumentSnapshot up_coming_appointment;

    //widgets
    private TextView
            tv_userFirstName, tv_userLastName, tv_userPhone, tv_userEmail, tv_userDocumentID,
            tv_upComingDateTime, tv_upComingDoctor, tv_upComingDoctorNumber, tv_upComingDateOfAction;
    private Button btn_booking;
    private RelativeLayout rl_upComing;
    private LinearLayout ll_upComingNo;

    private ImageView IV_Help, IV_Settings;

    //databse
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionDoctorReference = db.collection("Doctor");
    private CollectionReference collectionBookingReference = db.collection("Booking");

    public Fragment_Patient_Home() {
        // Required empty public constructor
    }

    public static Fragment_Patient_Home newInstance(
            String FName,String  LName,String  Phone,String Email) {
        Fragment_Patient_Home fragment = new Fragment_Patient_Home();
        Bundle bundle = new Bundle();

        bundle.putString(firstNameKey,FName);
        bundle.putString(lastNameKey,LName);
        bundle.putString(phoneKey,Phone);
        bundle.putString(emailKey,Email);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            patient_first_name = getArguments().getString(firstNameKey);
            patient_last_name = getArguments().getString(lastNameKey);
            patient_phone = getArguments().getString(phoneKey);
            patient_email = getArguments().getString(emailKey);
        }
        date_today = formatDate(dateToday.getTime());

    }//End of onCrete


    @SuppressLint("SetTextI18n")
    @Nullable
    @Override  //Cycle After onCrete
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_patient, container, false);

        tv_userFirstName = view.findViewById(R.id.TV_F_Patient_First_Name);
        tv_userLastName = view.findViewById(R.id.TV_F_Patient_Last_Name);
        tv_userPhone = view.findViewById(R.id.TV_F_Patient_Phone);
        tv_userEmail = view.findViewById(R.id.TV_F_Patient_Email);
        tv_userDocumentID = view.findViewById(R.id.TV_F_Patient_userID);

        tv_upComingDateTime = view.findViewById(R.id.TV_F_patient_upComing_dateTime);
        tv_upComingDoctor = view.findViewById(R.id.TV_F_patient_upComing_doctor);
        tv_upComingDoctorNumber = view.findViewById(R.id.TV_F_patient_upComing_doctor_phone);
        tv_upComingDateOfAction = view.findViewById(R.id.TV_F_patient_upComing_dateOfAction);

        rl_upComing = view.findViewById(R.id.RL_F_patient_upComing);
        ll_upComingNo = view.findViewById(R.id.LL_patient_upComingNo);

        tv_userFirstName.setText(patient_first_name);
        tv_userLastName.setText(patient_last_name);
        tv_userPhone.setText(patient_phone);
        tv_userEmail.setText(patient_email);
        tv_userDocumentID.setText("Login ID : " + patient_phone);

        btn_booking = view.findViewById(R.id.B_F_Patient_Book);

        IV_Help = view.findViewById(R.id.IV_Help);
        IV_Settings = view.findViewById(R.id.IV_Setting);

        return view;
    }//end of onCreteView

    @SuppressLint("SetTextI18n")
    @Override  //Cycle After onCreteView
    public void onStart() {
        super.onStart();

        getUpcomingBooking();

        btn_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Activity_Patient_Booking_Select_Doctor.class);
                intent.putExtra(phoneKey, patient_phone);
                intent.putExtra(emailKey, patient_email);
                intent.putExtra(firstNameKey,patient_first_name);
                intent.putExtra(lastNameKey,patient_last_name);
                startActivity(intent);
            }
        });

        IV_Help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Activity_Patient_Help.class);
                startActivity(intent);
            }
        });
        IV_Settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), Activity_Patient_Settings.class);
                startActivity(intent);
            }
        });


    }//end of onStart

    private String formatDate(long date){
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat(dateFormatPatten);
        return format.format(date);
    }

    private void getUpcomingBooking() {
//        Calendar calendar=Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY)+2);
//        Date currentDateTime = calendar.getTime();
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY));
        Date currentDateTime = calendar.getTime();
        collectionBookingReference
                .whereEqualTo("patient_documentID", patient_phone)
                .whereGreaterThan("timestamp",currentDateTime)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.getResult().isEmpty()){
                            ll_upComingNo.setVisibility(View.VISIBLE);
                            rl_upComing.setVisibility(View.GONE);
                        }else {
                            setupUpomingCard(task.getResult().getDocuments().get(0));
                        }

                    }
                });
    }

    @SuppressLint("SetTextI18n")
    private void setupUpomingCard(DocumentSnapshot document) {
        ll_upComingNo.setVisibility(View.GONE);
        rl_upComing.setVisibility(View.VISIBLE);
        Note_Booking note_booking = new Note_Booking();
        note_booking = document.toObject(Note_Booking.class);

        tv_upComingDateTime.setText(note_booking.getDate() + " " + note_booking.getTime());
        tv_upComingDoctor.setText("Dr. " + note_booking.getDoctor_fullName());
        tv_upComingDoctorNumber.setText("Tel: " + note_booking.getDoctor_documentID());
        tv_upComingDateOfAction.setText("Booked on : " + note_booking.getDateOfAction());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onResume() {
        super.onResume();
    }
}