package kmitl.proj.jittakan58070012.takemehomedemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

import java.util.ArrayList;
import java.util.List;

import kmitl.proj.jittakan58070012.takemehomedemo.CommonSharePreference;
import kmitl.proj.jittakan58070012.takemehomedemo.R;
import kmitl.proj.jittakan58070012.takemehomedemo.User_Drawer_option;
import kmitl.proj.jittakan58070012.takemehomedemo.adapter.dataAdapter;
import kmitl.proj.jittakan58070012.takemehomedemo.adapter.allAdapter;
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

        listtodisplay = new ArrayList<>();
        userprofile = new userProfile();
        
        recyclerView = rootView.findViewById(R.id.listHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d("HomeFragCK Name1", "displayall: " + commonSharePreference.read("username"));

        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("User");
        userRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                
                for (DataSnapshot userdata : dataSnapshot.getChildren()){
                    Log.d("HomeFragCK Name", "displayall: " + userdata.getKey());
                    Log.d("checkMultiuser", "onChildAdded: "+ userdata.child("name").getValue() + "   " + commonSharePreference.read("username"));
                    if (!userdata.child("name").getValue().equals(commonSharePreference.read("username"))){
                        userprofile = userdata.getValue(userprofile.getClass());
                        listtodisplay.add(userprofile);
                    }

                }
                Log.d("check Multiuser2", "onChildAdded" + listtodisplay.size());
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

    public void setRecyclerViewlistener(){
        recyclerView = rootView.findViewById(R.id.listHome);
    }

    public void recyclerOnclick(){
        kmitl.proj.jittakan58070012.takemehomedemo.adapter.allAdapter.cardViewsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("sss", "onClick: ");
            }
        });
    }

}
