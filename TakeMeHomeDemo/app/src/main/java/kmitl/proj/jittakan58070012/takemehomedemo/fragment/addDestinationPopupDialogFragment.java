package kmitl.proj.jittakan58070012.takemehomedemo.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import kmitl.proj.jittakan58070012.takemehomedemo.CommonSharePreference;
import kmitl.proj.jittakan58070012.takemehomedemo.MainActivity;
import kmitl.proj.jittakan58070012.takemehomedemo.R;
import kmitl.proj.jittakan58070012.takemehomedemo.User_Drawer_option;
import kmitl.proj.jittakan58070012.takemehomedemo.model.Constant;
import kmitl.proj.jittakan58070012.takemehomedemo.model.NewDrivecourse;
import kmitl.proj.jittakan58070012.takemehomedemo.model.seat;
import kmitl.proj.jittakan58070012.takemehomedemo.model.userProfile;

/**
 * Created by 58070012 on 11/16/2017.
 */





public class addDestinationPopupDialogFragment extends Fragment{

    private Calendar myCalendar;
    private EditText edittext;
    private EditText editTime;
    private CommonSharePreference commonSharePreference;
    private DatePickerDialog.OnDateSetListener date;
    private List<NewDrivecourse> newDrivecourseslist;
    private DatabaseReference userRef;
    private NewDrivecourse newDrivecourse;
    private String key;
    private seat seat;
    private List<seat> listseat;
    private userProfile userProfile;

    EditText seatAmount  ;
    EditText seatCost  ;

    private String stringAmount;
    private String stringSeatCost;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.popup_dialog_fragment_layout,container,false);

        commonSharePreference = new CommonSharePreference(getContext());
        Log.d("CheckCreate", "onCreateView: " + commonSharePreference.read("create"));
        edittext = rootView.findViewById(R.id.Date);
        edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDatepicker(view);
            }
        });

        editTime = rootView.findViewById(R.id.Time);
        editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createTime(view);
            }
        });

        seatAmount  = rootView.findViewById(R.id.seatAmount);
        seatCost  = rootView.findViewById(R.id.seatCost);

        this.stringAmount = seatAmount.getText().toString();
        this.stringSeatCost = seatAmount.getText().toString();


            Button apply_BTN = rootView.findViewById(R.id.Apply);
            apply_BTN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    apply(rootView);
                    backToDriver();


                }
            });

        Log.d("checkaddDia", "onCreateView: "+commonSharePreference.read("username"));

        return rootView;
    }

    public void createDatepicker(View v) {

        myCalendar = Calendar.getInstance();
        int startYear = myCalendar.get(Calendar.YEAR);
        int startMonth = myCalendar.get(Calendar.MONTH);
        int startDay = myCalendar.get(Calendar.DAY_OF_MONTH);

        edittext = v.findViewById(R.id.Date);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        edittext.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                    }
                }, startYear, startMonth, startDay);

        datePickerDialog.show();

    }

    public void createTime(View v) {

        myCalendar = Calendar.getInstance();
        int hour = myCalendar.get(Calendar.HOUR_OF_DAY);
        int minute = myCalendar.get(Calendar.MINUTE);

        edittext = v.findViewById(R.id.Time);
        TimePickerDialog mTimePicker;

        mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                edittext.setText( selectedHour + ":" + selectedMinute);
            }
        }, hour, minute, true);//Yes 24 hour time

        mTimePicker.setTitle("Select Time");
        mTimePicker.show();


    }

    public void apply(View v){

        newDrivecourse = new NewDrivecourse();
        seat = new seat();

        int count = 0;



        EditText start = v.findViewById(R.id.StartCreate);
        EditText des  = v.findViewById(R.id.DestinationCreate);
        EditText contact  =v.findViewById(R.id.Contact);
        EditText time  = v.findViewById(R.id.Time);
        EditText date  = v.findViewById(R.id.Date);
        EditText seatAmount  = v.findViewById(R.id.seatAmount);
        EditText seatCost  = v.findViewById(R.id.seatCost);
        EditText carBrand  = v.findViewById(R.id.carBrand);
        EditText carModel  = v.findViewById(R.id.carModel);
        EditText licenseplate  = v.findViewById(R.id.licenseplate);
        EditText color  = v.findViewById(R.id.Color);


        newDrivecourse.setStart(start.getText().toString());
        newDrivecourse.setDestination(des.getText().toString());
        newDrivecourse.setContact(contact.getText().toString());
        newDrivecourse.setTime(time.getText().toString());
        newDrivecourse.setDate(date.getText().toString());
        newDrivecourse.setSeatAmount(Integer.parseInt(seatAmount.getText().toString()));
        newDrivecourse.setSeatCost(Integer.parseInt(seatCost.getText().toString()));
        newDrivecourse.setCarbrand(carBrand.getText().toString());
        newDrivecourse.setModel(carModel.getText().toString());
        newDrivecourse.setPlate(licenseplate.getText().toString());
        newDrivecourse.setColor(color.getText().toString());



        userProfile = new userProfile();

        //Log.d("in", "apply: "+commonSharePreference.read("createstate"));
        if (commonSharePreference.read("createstate").toString().equals("create")){
            userRef = FirebaseDatabase.getInstance().getReference("User");
            userRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    for (DataSnapshot userdata : dataSnapshot.getChildren()){
                        if (userdata.child(commonSharePreference.read("username").toString()).exists()){
                            User_Drawer_option.userProfile = userdata.child(commonSharePreference.read("username").toString()).child(userdata.getKey()).getValue(User_Drawer_option.userProfile.getClass());
                            User_Drawer_option.userProfile.getDriverCourse().add(newDrivecourse);
                            userRef.child(userdata.getKey()).setValue(User_Drawer_option.userProfile.getDriverCourse());
                            break;
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else{
            userRef = FirebaseDatabase.getInstance().getReference("User");
            Constant.createState = "create";
            commonSharePreference.save("createstate","create");
            //Log.d("create", "onDataChange: adddrifrag");
            //Log.d("CK LINK ID", "apply: " + User_Drawer_option.userProfile.getId() + "  " + User_Drawer_option.userProfile.getLink());
            newDrivecourseslist = new ArrayList<>();
            listseat = new ArrayList<>();
            for (int i= 0;i < newDrivecourse.getSeatAmount();i++){
                seat.setUser("");
                seat.setId("");
                listseat.add(seat);
            }
            newDrivecourse.setSeat(listseat);
            newDrivecourseslist.add(newDrivecourse);
            User_Drawer_option.userProfile.setDriverCourse(newDrivecourseslist);
            userRef.child(commonSharePreference.read("username").toString()).push().setValue(User_Drawer_option.userProfile);

        }
    }


    public void backToDriver() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        getFragmentManager().popBackStack();
        transaction.replace(R.id.FragmentContainer,new DriverFragment());
        transaction.commit();

    }

}

