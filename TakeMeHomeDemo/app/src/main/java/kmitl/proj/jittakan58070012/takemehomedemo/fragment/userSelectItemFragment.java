package kmitl.proj.jittakan58070012.takemehomedemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import kmitl.proj.jittakan58070012.takemehomedemo.CommonSharePreference;
import kmitl.proj.jittakan58070012.takemehomedemo.R;
import kmitl.proj.jittakan58070012.takemehomedemo.adapter.allAdapter;
import kmitl.proj.jittakan58070012.takemehomedemo.model.userProfile;

/**
 * A simple {@link Fragment} subclass.
 */
public class userSelectItemFragment extends Fragment {


    private List<userProfile> listtodisplay;
    private userProfile userprofile;
    private CommonSharePreference commonSharePreference;
    private int listposition;
    private int count;
    private String key;

    public userSelectItemFragment() {
        // Required empty public constructor
        this.count = 0;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.userselect, container, false);
        commonSharePreference = new CommonSharePreference(getContext());
        display(allAdapter.position, rootView);
        return rootView;
    }

    public void display(final int pos, View rootView){
        listtodisplay = new ArrayList<>();
        userprofile = new userProfile();
        this.count=0;
        this.listposition = pos;
        final EditText start = rootView.findViewById(R.id.userSelecctStart);
        final EditText des  = rootView.findViewById(R.id.userSelecctDestination);
        final EditText contact  =rootView.findViewById(R.id.userSelecctContact);
        final EditText time  = rootView.findViewById(R.id.userSelecctTime);
        final EditText date  = rootView.findViewById(R.id.userSelecctDate);
        final EditText seatAmount  = rootView.findViewById(R.id.userSelecctseatAmount);
        final EditText seatCost  = rootView.findViewById(R.id.userSelecctseatCost);
        final EditText carBrand  = rootView.findViewById(R.id.userSelecctcarBrand);
        final EditText carModel  = rootView.findViewById(R.id.userSelecctcarModel);
        final EditText licenseplate  = rootView.findViewById(R.id.userSelecctlicenseplate);
        final EditText color  = rootView.findViewById(R.id.userSelecctColor);
        final TextView username = rootView.findViewById(R.id.userSelectname);

        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("User");
        userRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for (DataSnapshot userdisplay : dataSnapshot.getChildren()){
                    Log.d("Count CHK", "onChildAdded: " + count+ " ");
                    if (!userdisplay.child("name").getValue().equals(commonSharePreference.read("username")) && count == listposition){
                        Log.d("check in", "onChildAdded: in");
                        userprofile = userdisplay.getValue(userprofile.getClass());
                        listtodisplay.add(userprofile);
                        key = userdisplay.getKey();
                        start.setText(listtodisplay.get(0).getDriverCourse().get(0).getStart());
                        des.setText(listtodisplay.get(0).getDriverCourse().get(0).getDestination());
                        contact.setText(listtodisplay.get(0).getDriverCourse().get(0).getContact());
                        time.setText(listtodisplay.get(0).getDriverCourse().get(0).getTime());
                        date.setText(listtodisplay.get(0).getDriverCourse().get(0).getDate());
                        seatAmount.setText(String.valueOf(listtodisplay.get(0).getDriverCourse().get(0).getSeatAmount()));
                        seatCost.setText(String.valueOf(listtodisplay.get(0).getDriverCourse().get(0).getSeatCost()));
                        carBrand.setText(listtodisplay.get(0).getDriverCourse().get(0).getCarbrand());
                        carModel.setText(listtodisplay.get(0).getDriverCourse().get(0).getModel());
                        licenseplate.setText(listtodisplay.get(0).getDriverCourse().get(0).getPlate());
                        color.setText(listtodisplay.get(0).getDriverCourse().get(0).getColor());
                        username.setText(listtodisplay.get(0).getName());
                        break;
                    }
                    Log.d("CKSize", "onChildAdded: " + listtodisplay.size());

                    count+=1;
                }




            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Button applyButton = rootView.findViewById(R.id.userSelecctApply);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference updateValue = FirebaseDatabase.getInstance().getReference().child("User").child(commonSharePreference.read("username").toString())
                        .child(key);
               listtodisplay.get(0).getDriverCourse().get(0).setSeatAmount(listtodisplay.get(0).getDriverCourse().get(0).getSeatAmount()-1);
                updateValue.setValue(listtodisplay.get(0));

                backToHome();
            }
        });

    }

    public void backToHome() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        getFragmentManager().popBackStack();
        transaction.replace(R.id.FragmentContainer,new HomeFragment());
        transaction.commit();

    }

}
