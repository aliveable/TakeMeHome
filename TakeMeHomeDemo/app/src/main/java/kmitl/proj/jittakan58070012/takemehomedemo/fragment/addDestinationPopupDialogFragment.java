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
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import kmitl.proj.jittakan58070012.takemehomedemo.MainActivity;
import kmitl.proj.jittakan58070012.takemehomedemo.R;
import kmitl.proj.jittakan58070012.takemehomedemo.model.NewDrivecourse;
import kmitl.proj.jittakan58070012.takemehomedemo.model.userProfile;

/**
 * Created by 58070012 on 11/16/2017.
 */





public class addDestinationPopupDialogFragment extends Fragment implements View.OnClickListener {

    private Calendar myCalendar;
    private EditText edittext;
    private EditText editTime;
    private userProfile userProfile;
    private DatePickerDialog.OnDateSetListener date;
    private List<NewDrivecourse> newDrivecourseslist;


    public static addDestinationPopupDialogFragment newInstance() {

        Bundle args = new Bundle();

        addDestinationPopupDialogFragment fragment = new addDestinationPopupDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.popup_dialog_fragment_layout,container,false);



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
        NewDrivecourse newDrivecourse = new NewDrivecourse();
        newDrivecourseslist = null;

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
        newDrivecourseslist.add(newDrivecourse);
        userProfile.setDriverCourse(newDrivecourseslist);


    }

    @Override
    public void onClick(View v) {
        apply(v);
    }
}
