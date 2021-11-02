package com.example.patienttracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import java.util.HashMap;
import java.util.Map;

public class
Activity_Doctor extends AppCompatActivity {

    //Variables
    public static final String TAG = "DoctorActivity";

    private Map<String, Object> doctorData = new HashMap<>();

    private String userPhone = "";
    private String userEmail = "";
    private String userPassword = "";
    private String userFName = "";
    private String userLName = "";
    private String userID = "";
    private String userFields = "";
    private String userQualifications = "";
    private String userYears = "";

    //Fragment
    private Fragment_Doctor_Home fragment_doctor_home;
    private Fragment_Doctor_Upcoming fragment_doctor_upcoming;
    private Fragment_Doctor_Availability fragment_doctor_availability;
    private Fragment_Doctor_History fragment_doctor_history;
    //Widgets
    private MeowBottomNavigation meowBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_main);

        //get variables from previous activity
        final Intent intent = getIntent();
        doctorData = (Map<String, Object>) intent.getSerializableExtra(Activity_Doctor_Login.doctorDataKEY);
        userPhone           = intent.getStringExtra(Activity_Doctor_Login.doctorPhoneKEY);
        userEmail           = (String) doctorData.get("Email");
        userPassword        = (String) doctorData.get("Password");
        userFName           = (String) doctorData.get("FirstName");
        userLName           = (String) doctorData.get("LastName");
        userID              = (String) doctorData.get("ID");
        userFields          = (String) doctorData.get("Fields");
        userQualifications  = (String) doctorData.get("Qualifications");
        userYears           = (String) doctorData.get("Years");

        //send data to Each fragment
        fragment_doctor_home = Fragment_Doctor_Home.newInstance(
                userFName,userLName,userPhone,userEmail,userFields,userQualifications,userYears);
        fragment_doctor_upcoming = fragment_doctor_upcoming.newInstance(userPhone);
        fragment_doctor_availability = Fragment_Doctor_Availability.newInstance(userPhone);

        fragment_doctor_history = Fragment_Doctor_History.newInstance(userPhone);
        //Assign variable
        meowBottomNavigation = findViewById(R.id.bottom_navigation);

        //Add Menu items to Bottom Navigation
        meowBottomNavigation.add(new MeowBottomNavigation.Model(1,R.drawable.ic_person));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(2,R.drawable.ic_availability));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(3,R.drawable.ic_booking));
        meowBottomNavigation.add(new MeowBottomNavigation.Model(4,R.drawable.ic_time));

        //Accessing Menu items
        meowBottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {

                //Initialize fragment
                Fragment fragment= null;
                //Menu item selector
                switch (item.getId()){
                    case 1://When id is 1 Doctor Home Page
                        loadFragment(fragment_doctor_home);
                        break;
                    case 2://When id is 2 Doctor upcoming Page
//                        loadFragment(fragment_doctor_search);
                        loadFragment(fragment_doctor_upcoming);
                        break;
                    case 3: //when id is 3 Doctor History
                        loadFragment(fragment_doctor_history);
                        break;
                    case 4://When id is 4 Doctor Availability
                        loadFragment(fragment_doctor_availability);
                        break;


                }
            }
        });

        //Set Doctor Home Page to Default
        meowBottomNavigation.show(1,true);

        //When Select A Menu Item Do ...
        meowBottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //
            }
        });
        meowBottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {
                //
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        //Replace Fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout,fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Activity_Doctor_Login.class);
        startActivity(intent);
    }//end of onBackPressed

}