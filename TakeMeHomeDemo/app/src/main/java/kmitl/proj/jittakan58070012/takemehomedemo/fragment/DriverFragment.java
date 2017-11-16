package kmitl.proj.jittakan58070012.takemehomedemo.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kmitl.proj.jittakan58070012.takemehomedemo.R;

/**
 * A simple {@link Fragment} subclass.
 */


public class DriverFragment extends Fragment{


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





}
