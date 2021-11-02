package com.example.patienttracker;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Fragment_Doctor_Upcoming extends Fragment {

    //variables
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mlayoutManager;

    private static final String TAG                 = "FragmentDoctorHome";
    private static final String phoneKey            = "phonenumber";

    public static final String dateFormatPatten = "yyyy-MM-dd";

    private String myuserphone ;
    private String date_today;
    private final Date dateToday = new Date();

    //database
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionDoctorReference = db.collection("Doctor");
    private CollectionReference collectionBookingReference = db.collection("Booking");
    private CollectionReference collectionPatientReference = db.collection("Patient");

    public Fragment_Doctor_Upcoming() {
        // Required empty public constructor
    }

    public static Fragment_Doctor_Upcoming newInstance(String Phone) {
        Fragment_Doctor_Upcoming fragment = new Fragment_Doctor_Upcoming();
        Bundle args = new Bundle();
        args.putString(phoneKey,Phone);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Log.d(TAG, "onCreate: Arguments NOT null");

            myuserphone = getArguments().getString(phoneKey);
        }
        date_today = formatDate(dateToday.getTime());
    }//End of onCrete

    @Nullable
    @Override  //Cycle After onCrete
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doctor_history, container, false);

        loadAppointments();
        return view;
    }//end of onCreteView


    private void loadAppointments(){

    }
    private String  formatDate(long date){
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat format = new SimpleDateFormat(dateFormatPatten);
        return format.format(date);
    }

    private Boolean checkDate(String date) throws ParseException {
        Boolean before = false;
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormatPatten);
        Date documentDate = sdf.parse(date);
        Date today = sdf.parse(date_today);
        if (!(today.equals(documentDate) || today.after(documentDate))) {
            before = true;
        }
        return before;
    }


    private String getTime(String time,Boolean isHalfHourSlot) {
        String temp_time_string = "Error Getting Time";

        if (isHalfHourSlot){
            switch (time){
                case "time01":
                    temp_time_string = "00:00";
                    break;
                case "time02":
                    temp_time_string = "00:30";
                    break;
                case "time03":
                    temp_time_string = "01:00";
                    break;
                case "time04":
                    temp_time_string = "01:30";
                    break;

                case "time05":
                    temp_time_string = "02:00";
                    break;
                case "time06":
                    temp_time_string = "02:30";
                    break;
                case "time07":
                    temp_time_string = "03:00";
                    break;
                case "time08":
                    temp_time_string = "03:30";
                    break;

                case "time09":
                    temp_time_string = "04:00";
                    break;
                case "time10":
                    temp_time_string = "04:30";
                    break;
                case "time11":
                    temp_time_string = "05:00";
                    break;
                case "time12":
                    temp_time_string = "05:30";
                    break;

                case "time13":
                    temp_time_string = "06:00";
                    break;
                case "time14":
                    temp_time_string = "06:30";
                    break;
                case "time15":
                    temp_time_string = "07:00";
                    break;
                case "time16":
                    temp_time_string = "07:30";
                    break;

                case "time17":
                    temp_time_string = "08:00";
                    break;
                case "time18":
                    temp_time_string = "08:30";
                    break;
                case "time19":
                    temp_time_string = "09:00";
                    break;
                case "time20":
                    temp_time_string = "09:30";
                    break;

                case "time21":
                    temp_time_string = "10:00";
                    break;
                case "time22":
                    temp_time_string = "10:30";
                    break;
                case "time23":
                    temp_time_string = "11:00";
                    break;
                case "time24":
                    temp_time_string = "11:30";
                    break;

                case "time25":
                    temp_time_string = "12:00";
                    break;
                case "time26":
                    temp_time_string = "12:30";
                    break;
                case "time27":
                    temp_time_string = "13:00";
                    break;
                case "time28":
                    temp_time_string = "13:30";
                    break;

                case "time29":
                    temp_time_string = "14:00";
                    break;
                case "time30":
                    temp_time_string = "14:30";
                    break;
                case "time31":
                    temp_time_string = "15:00";
                    break;
                case "time32":
                    temp_time_string = "15:30";
                    break;

                case "time33":
                    temp_time_string = "16:00";
                    break;
                case "time34":
                    temp_time_string = "16:30";
                    break;
                case "time35":
                    temp_time_string = "17:00";
                    break;
                case "time36":
                    temp_time_string = "17:30";
                    break;

                case "time37":
                    temp_time_string = "18:00";
                    break;
                case "time38":
                    temp_time_string = "18:30";
                    break;
                case "time39":
                    temp_time_string = "19:00";
                    break;
                case "time40":
                    temp_time_string = "19:30";
                    break;

                case "time41":
                    temp_time_string = "20:00";
                    break;
                case "time42":
                    temp_time_string = "20:30";
                    break;
                case "time43":
                    temp_time_string = "21:00";
                    break;
                case "time44":
                    temp_time_string = "21:30";
                    break;

                case "time45":
                    temp_time_string = "22:00";
                    break;
                case "time46":
                    temp_time_string = "22:30";
                    break;
                case "time47":
                    temp_time_string = "23:00";
                    break;
                case "time48":
                    temp_time_string = "23:30";
                    break;
            }

        }else {
            switch (time){
                case "time01":
                    temp_time_string = "00:00";
                    break;
                case "time02":
                    temp_time_string = "01:00";
                    break;
                case "time03":
                    temp_time_string = "02:00";
                    break;
                case "time04":
                    temp_time_string = "03:00";
                    break;

                case "time05":
                    temp_time_string = "04:00";
                    break;
                case "time06":
                    temp_time_string = "05:00";
                    break;
                case "time07":
                    temp_time_string = "06:00";
                    break;
                case "time08":
                    temp_time_string = "07:00";
                    break;

                case "time09":
                    temp_time_string = "08:00";
                    break;
                case "time10":
                    temp_time_string = "09:00";
                    break;
                case "time11":
                    temp_time_string = "10:00";
                    break;
                case "time12":
                    temp_time_string = "11:00";
                    break;

                case "time13":
                    temp_time_string = "12:00";
                    break;
                case "time14":
                    temp_time_string = "13:00";
                    break;
                case "time15":
                    temp_time_string = "14:00";
                    break;
                case "time16":
                    temp_time_string = "15:00";
                    break;

                case "time17":
                    temp_time_string = "16:00";
                    break;
                case "time18":
                    temp_time_string = "17:00";
                    break;
                case "time19":
                    temp_time_string = "18:00";
                    break;
                case "time20":
                    temp_time_string = "19:00";
                    break;

                case "time21":
                    temp_time_string = "20:00";
                    break;
                case "time22":
                    temp_time_string = "21:00";
                    break;
                case "time23":
                    temp_time_string = "22:00";
                    break;
                case "time24":
                    temp_time_string = "23:00";
                    break;
            }
        }

        return temp_time_string;
    }
    @SuppressLint("SetTextI18n")
    @Override  //Cycle After onCreteView
    public void onStart() {
        super.onStart();


    }//end of onStart

}