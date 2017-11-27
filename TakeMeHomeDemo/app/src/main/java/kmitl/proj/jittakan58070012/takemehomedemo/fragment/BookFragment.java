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
import kmitl.proj.jittakan58070012.takemehomedemo.adapter.selectAdapter;
import kmitl.proj.jittakan58070012.takemehomedemo.model.userProfile;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends Fragment {

    private CommonSharePreference commonSharePreference;
    private userProfile userProfile;
    private List<userProfile> listtodisplay;
    private List<userProfile> listtodisplayshow;
    private RecyclerView recyclerView;
    private selectAdapter selectAdapter;
    private allAdapter allAdapter;
    int count;
    private long allnodecount;

    public BookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_book, container, false);
        commonSharePreference = new CommonSharePreference(getContext());
        display(rootView);
        return rootView;

    }

    public void display(View rootView){
        count=0;
        allnodecount = 0;
        userProfile = new userProfile();
        listtodisplay = new ArrayList<>();
        listtodisplayshow = new ArrayList<>();
        recyclerView = rootView.findViewById(R.id.listBook);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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
                for (DataSnapshot displayitem : dataSnapshot.getChildren()) {
                    Log.d("DPI", "onChildAdded: " + displayitem.getValue().toString());

                    userProfile = displayitem.getValue(userProfile.getClass());
                    listtodisplay.add(userProfile);
                    count += 1;
                    Log.d("check value", "onChildAdded: " + displayitem.child("id").getValue().toString() + "  " + commonSharePreference.read("id").toString());
                    Log.d("check value", "onChildAdded: " + displayitem.child("name").getValue().toString() + "  " + commonSharePreference.read("username").toString());

                    if (allnodecount == listtodisplay.size()) {
                        outerloop:
                        for (int i = 0; i < listtodisplay.size(); i++) {
                            Log.d("CK loop", "onChildAdded: 1" + i);
                            for (int j = 0; j < listtodisplay.get(i).getDriverCourse().size(); j++) {
                                Log.d("CK loop", "onChildAdded: 2" + j);
                                for (int k = 0; k < listtodisplay.get(i).getDriverCourse().get(j).getSeat().size(); k++) {
                                    Log.d("CK loop", "onChildAdded: 3" + k);
                                    if (listtodisplay.get(i).getDriverCourse().get(j).getSeat().get(k).getUser().equals(commonSharePreference.read("username")) &&
                                            listtodisplay.get(i).getDriverCourse().get(j).getSeat().get(k).getId().equals(commonSharePreference.read("id"))) {
                                        Log.d("CK loop", "onChildAdded: 3 in precise" + k + "   " + listtodisplay.get(i).getName());
                                        Log.d("CK loop", "onChildAdded: 3 in precise" + k + "   " + listtodisplay.get(i).getDriverCourse().get(j).getSeat().get(k).getUser());
                                        Log.d("CK loop", "onChildAdded: 3 in precise" + k + "   " + listtodisplay.get(i).getDriverCourse().get(j).getSeat().get(k).getId());
                                        listtodisplayshow.add(listtodisplay.get(i));
                                        Log.d("Addcheck ", "onChildAdded: Added");



                                    }

                                }

                            }
                        }

                    }
                }

                Log.d("size", "onChildAdded: "+ listtodisplayshow.size());

                selectAdapter = new selectAdapter((AppCompatActivity) getActivity(), listtodisplayshow);
                recyclerView.setAdapter(selectAdapter);
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
