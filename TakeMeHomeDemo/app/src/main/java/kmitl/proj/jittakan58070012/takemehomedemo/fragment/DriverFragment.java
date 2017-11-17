package kmitl.proj.jittakan58070012.takemehomedemo.fragment;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import kmitl.proj.jittakan58070012.takemehomedemo.R;
import kmitl.proj.jittakan58070012.takemehomedemo.User_Drawer_option;
import kmitl.proj.jittakan58070012.takemehomedemo.adapter.dataAdapter;

/**
 * A simple {@link Fragment} subclass.
 */


public class DriverFragment extends Fragment{


    private dataAdapter dataadapter;
    private RecyclerView recyclerView;

    public DriverFragment() {
        // Required empty public constructor

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_driver, container, false);
        FloatingActionButton fab = rootview.findViewById(R.id.AddDriver);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAlert();
            }
        });
        Display(rootview);
        return rootview;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void openAlert() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.FragmentContainer,new addDestinationPopupDialogFragment());
        transaction.addToBackStack(null);
        transaction.commit();

    }

    public void Display(View rootview){
        recyclerView = rootview.findViewById(R.id.list);
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("User");
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userdata : dataSnapshot.getChildren()){
                    User_Drawer_option.userProfile = userdata.getValue(User_Drawer_option.userProfile.getClass());
                    dataadapter = new dataAdapter(getActivity(), User_Drawer_option.userProfile.getDriverCourse());
                    recyclerView.setAdapter(dataadapter);
                }



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }





}
