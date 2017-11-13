package kmitl.proj.jittakan58070012.takemehomedemo.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import kmitl.proj.jittakan58070012.takemehomedemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DriverFragment extends Fragment implements View.OnClickListener{


    public DriverFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_driver, container, false);

        return rootview;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.AddDriver){

        }
    }



}
