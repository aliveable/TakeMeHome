package kmitl.proj.jittakan58070012.takemehomedemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import kmitl.proj.jittakan58070012.takemehomedemo.adapter.dataAdapter;
import kmitl.proj.jittakan58070012.takemehomedemo.adapter.selectAdapter;
import kmitl.proj.jittakan58070012.takemehomedemo.model.userProfile;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private View rootView;
    private dataAdapter dataadapter;
    private allAdapter allAdapter;
    private RecyclerView recyclerView;
    private userProfile userprofile;
    private List<userProfile> listtodisplay;
    private CommonSharePreference commonSharePreference;
    private long countsame;
    private long allnodecount;
    private int countfull;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        commonSharePreference = new CommonSharePreference(getContext());
        displayall(rootView);
        //recyclerOnclick();
        return rootView;
    }

    public void displayall(View rootView){
        this.countsame = 0;
        this.allnodecount = 0;
        this.countfull = 0;
        listtodisplay = new ArrayList<>();
        userprofile = new userProfile();
        
        recyclerView = rootView.findViewById(R.id.showItemlistHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d("HomeFragCK Name1", "displayall: " + commonSharePreference.read("username"));

        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("User");
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot user : dataSnapshot.getChildren()){
                    Log.d("countsnapsingle", "onDataChange: " + dataSnapshot.child(user.getKey()).getChildrenCount());
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
                
                for (DataSnapshot userdata : dataSnapshot.getChildren()){
                    //Log.d("HomeFragCK Name", "displayall: " + userdata.getKey());
                    Log.d("checkMultiuser", "onChildAdded: "+ userdata.child("name").getValue() + "   " + commonSharePreference.read("username"));
                    if (!userdata.child("name").getValue().equals(commonSharePreference.read("username"))){

                        userprofile = userdata.getValue(userprofile.getClass());
                        listtodisplay.add(userprofile);

                        Log.d("save", "onChildAdded: " + userdata.getValue());
                        Log.d("checksize", "onChildAdded: " + listtodisplay.size());
                    }else if (userdata.child("name").getValue().equals(commonSharePreference.read("username"))){
                        countsame = dataSnapshot.getChildrenCount();
                        Log.d("samecount", "onChildAdded: " + countsame + "   " + allnodecount);
                    }


                    if (listtodisplay.size() == allnodecount - countsame){

                        outerloop:
                        for (int j = 0 ; j < listtodisplay.size(); j++) {
                            Log.d("dddd", "onChildAdded: in");
                            certer:
                            for (int i = 0; i < listtodisplay.get(j).getDriverCourse().size(); i++) {
                                Log.d("check in side seat", "onChildAdded: outloop");
                                countfull = 0;
                                for (int k = 0; k <listtodisplay.get(j).getDriverCourse().get(i).getSeat().size();k++){
                                   // Log.d("check in side seat", "onChildAdded: " +listtodisplay.get(j).getName()+ " "+ listtodisplay.get(j).getDriverCourse().get(i).getSeat().get(k).getId() + " " + listtodisplay.get(j).getDriverCourse().get(i).getSeat().get(k).getUser());
                                    Log.d("checksameremove", "onChildAdded: Node: "+listtodisplay.get(j).getName() +"   " +listtodisplay.get(j).getDriverCourse().get(i).getSeat().get(k).getUser() + " value:" + commonSharePreference.read("username").toString());
                                    if (listtodisplay.get(j).getDriverCourse().get(i).getSeat().get(k).getUser().equals(commonSharePreference.read("username").toString()) &&
                                            listtodisplay.get(j).getDriverCourse().get(i).getSeat().get(k).getId().equals(commonSharePreference.read("id").toString())) {

                                        Log.d("checkid2", "onChildAdded: in" + listtodisplay.get(j).getDriverCourse().get(i).getSeat().get(k).getUser().toString() + "    "
                                               +  listtodisplay.get(j).getDriverCourse().get(i).getSeat().get(k).getId().toString());
                                        listtodisplay.remove(j);
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
                    }

                }
                Log.d("check Multiuser2", "onChildAdded" + listtodisplay.size());

                for (userProfile data : listtodisplay){
                    Log.d("checkinsidelist", "onChildAdded: " + data.getName());
                }

                allAdapter = new allAdapter((AppCompatActivity) getActivity(), listtodisplay);
                recyclerView.setAdapter(allAdapter);
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



}
