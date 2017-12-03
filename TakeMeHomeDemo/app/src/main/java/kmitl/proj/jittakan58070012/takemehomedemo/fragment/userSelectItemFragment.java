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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import kmitl.proj.jittakan58070012.takemehomedemo.CommonSharePreference;
import kmitl.proj.jittakan58070012.takemehomedemo.R;
import kmitl.proj.jittakan58070012.takemehomedemo.adapter.allAdapter;
import kmitl.proj.jittakan58070012.takemehomedemo.adapter.selectAdapter;
import kmitl.proj.jittakan58070012.takemehomedemo.model.seat;
import kmitl.proj.jittakan58070012.takemehomedemo.model.userProfile;

/**
 * A simple {@link Fragment} subclass.
 */
public class userSelectItemFragment extends Fragment {


    private List<userProfile> listtodisplay;
    private List<userProfile> display;
    private userProfile userprofile;
    private CommonSharePreference commonSharePreference;
    public int listposition;
    private int count;
    private long sameacccount;
    private int allnodecount;
    private String key;
    private seat seat;
    private int check;
    private int countfull;

    public userSelectItemFragment newInstance(int pos) {

        Bundle args = new Bundle();
        listposition = pos;
        userSelectItemFragment fragment = new userSelectItemFragment();
        fragment.setArguments(args);
        return fragment;
    }

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

    public void display(final int pos, final View rootView){
        listtodisplay = new ArrayList<>();
        display = new ArrayList<>();
        userprofile = new userProfile();
        this.count=0;
        this.sameacccount = 0;
        this.listposition = pos;
        this.allnodecount = 0;
        check = 0;
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
        userRef.keepSynced(true);
       userRef.addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               for (DataSnapshot user : dataSnapshot.getChildren()){
                  // Log.d("countsnapsingle", "onDataChange: " + dataSnapshot.child(user.getKey()).getChildrenCount());
                   allnodecount += dataSnapshot.child(user.getKey()).getChildrenCount();
               }

           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
        userRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


                outloop:
                for (DataSnapshot userdisplay : dataSnapshot.getChildren()){

                    Log.d("Count CHK", "onChildAdded: " + count+ " " + listposition);
                    Log.d("check user", "onChildAdded: " +userdisplay.getValue() + "   " + commonSharePreference.read("username"));

                    if (!userdisplay.child("name").getValue().equals(commonSharePreference.read("username"))){
                        //Log.d("snapcount", "onChildAdded: " + dataSnapshot.getChildrenCount());
                            userprofile = userdisplay.getValue(userprofile.getClass());
                            listtodisplay.add(userprofile);


                        Log.d("check in", "onChildAdded: in" + key +  "   " + userdisplay.getValue() + "  " +dataSnapshot.getChildrenCount() + "  " + userdisplay.getChildrenCount()  );

                        Log.d("loopkeycheck", "onChildAdded: "+"Key :" + key+"  "+ userdisplay.getKey() + "   Size: " + listtodisplay.size());


                        if (userdisplay.child("name").getValue().equals(allAdapter.userCheck.getName())
                                && userdisplay.child("driverCourse").child("0").child("plate").getValue().equals(allAdapter.userCheck.getDriverCourse().get(0).getPlate())
                                && userdisplay.child("driverCourse").child("0").child("carbrand").getValue().equals(allAdapter.userCheck.getDriverCourse().get(0).getCarbrand())
                                && userdisplay.child("driverCourse").child("0").child("model").getValue().equals(allAdapter.userCheck.getDriverCourse().get(0).getModel())){
                            Log.d("checkPlate", "onChildAdded: " + allAdapter.userCheck.getDriverCourse().get(0).getPlate() + "   " + userdisplay.getKey());
                            key = userdisplay.getKey();
                        }



                    }else if (userdisplay.child("name").getValue().equals(commonSharePreference.read("username"))){
                        sameacccount = dataSnapshot.getChildrenCount();
                    }



                    Log.d("listsize chk", "onChildAdded: " + listtodisplay.size() + "  " + sameacccount);
                    if (listtodisplay.size() == allnodecount - sameacccount) {

                        Log.d("checkalladaplist", "onChildAdded: " + allAdapter.userCheck.getName());
                        Log.d("checkalladaplist", "onChildAdded: " + allAdapter.userCheck.getId());
                        Log.d("checkalladaplist", "onChildAdded: " + allAdapter.userCheck.getLink());
                        Log.d("check alladap list", "onChildAdded: " + allAdapter.userCheck.getDriverCourse().get(0).getPlate());



                        Log.d("check in", "onChildAdded: in" + key);
//                        if (sameacccount <= 2){
                            outerloop:
                        for (int j = 0 ; j < listtodisplay.size(); j++) {

                            Log.d("dddd", "onChildAdded: in");
                            certer:
                            for (int i = 0; i < listtodisplay.get(j).getDriverCourse().size(); i++) {
                                countfull = 0;
                                for (int k = 0; k <listtodisplay.get(j).getDriverCourse().get(i).getSeat().size();k++){

                                    Log.d("checkdisplay", "onChildAdded: "+listtodisplay.get(j).getDriverCourse().get(i).getSeat().get(k).getUser()) ;

                                    Log.d("checkdisplay", "onChildAdded: "+listtodisplay.get(j).getDriverCourse().get(i).getSeat().get(k).getId()) ;

                                    if (listtodisplay.get(j).getDriverCourse().get(i).getSeat().get(k).getUser().equals(commonSharePreference.read("username").toString()) &&
                                            listtodisplay.get(j).getDriverCourse().get(i).getSeat().get(k).getId().equals(commonSharePreference.read("id").toString())) {

                                        Log.d("check id2", "onChildAdded: in" + listtodisplay.get(j).getDriverCourse().get(i).getSeat().get(k).getUser().toString() + "    "
                                                +  listtodisplay.get(j).getDriverCourse().get(i).getSeat().get(k).getId().toString() + listtodisplay.get(j).getName());

                                        listtodisplay.remove(j);



                                        break outerloop;

                                    }

                                    if(!listtodisplay.get(j).getDriverCourse().get(i).getSeat().get(k).getUser().equals("") &&
                                            !listtodisplay.get(j).getDriverCourse().get(i).getSeat().get(k).getId().equals("") ) {

                                        countfull += 1;
                                        if (countfull == listtodisplay.get(j).getDriverCourse().get(i).getSeat().size()){
                                            Log.d("ssss", "onChildAdded: "  + countfull + "   " + listtodisplay.get(j).getDriverCourse().get(i).getSeat().size());
                                            listtodisplay.remove(j);
                                            countfull = 0;
                                            break certer;
                                        }
                                    }


                                }


                            }

                        }
//                        }



                        Log.d("list", "onChildAdded: " + listtodisplay.size());

                        start.setText(listtodisplay.get(listposition).getDriverCourse().get(0).getStart());
                        des.setText(listtodisplay.get(listposition).getDriverCourse().get(0).getDestination());
                        contact.setText(listtodisplay.get(listposition).getDriverCourse().get(0).getContact());
                        time.setText(listtodisplay.get(listposition).getDriverCourse().get(0).getTime());
                        date.setText(listtodisplay.get(listposition).getDriverCourse().get(0).getDate());
                        seatAmount.setText(String.valueOf(listtodisplay.get(listposition).getDriverCourse().get(0).getSeatAmount()));
                        seatCost.setText(String.valueOf(listtodisplay.get(listposition).getDriverCourse().get(0).getSeatCost()));
                        carBrand.setText(listtodisplay.get(listposition).getDriverCourse().get(0).getCarbrand());
                        carModel.setText(listtodisplay.get(listposition).getDriverCourse().get(0).getModel());
                        licenseplate.setText(listtodisplay.get(listposition).getDriverCourse().get(0).getPlate());
                        color.setText(listtodisplay.get(listposition).getDriverCourse().get(0).getColor());
                        username.setText(listtodisplay.get(listposition).getName());

                        //Log.d("CKSize", "onChildAdded: " + listtodisplay.size() + "  " + listposition);

                        Button applyButton = rootView.findViewById(R.id.userSelecctApply);
                        applyButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                seat = new seat();
                                seat.setId(listtodisplay.get(listposition).getId());
                                seat.setUser(listtodisplay.get(listposition).getName());

                                DatabaseReference updateValue = FirebaseDatabase.getInstance().getReference().child("User").child(listtodisplay.get(listposition).getName())
                                        .child(key);
                                Log.d("check", "onClick: " + key + "   ");
                                for (int index = 0 ;index < listtodisplay.get(listposition).getDriverCourse().get(0).getSeat().size();index++){
                                    Log.d("user select index", "onClick: " + index);
                                    if (listtodisplay.get(listposition).getDriverCourse().get(0).getSeat().get(index).getId().equals("")
                                            && listtodisplay.get(0).getDriverCourse().get(0).getSeat().get(index).getUser().equals("")){
                                        listtodisplay.get(listposition).getDriverCourse().get(0).getSeat().get(index).setId(commonSharePreference.read("id").toString());
                                        listtodisplay.get(listposition).getDriverCourse().get(0).getSeat().get(index).setUser(commonSharePreference.read("username").toString());

                                        break;
                                    }
                                }

                                updateValue.setValue(listtodisplay.get(listposition));

                                backToHome();
                            }
                        });

                    }
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





    }

    public void backToHome() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        getFragmentManager().popBackStack();
        transaction.replace(R.id.FragmentContainer,new HomeFragment());
        transaction.commit();

    }

}
