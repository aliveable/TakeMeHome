package kmitl.proj.jittakan58070012.takemehomedemo.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kmitl.proj.jittakan58070012.takemehomedemo.R;
import kmitl.proj.jittakan58070012.takemehomedemo.fragment.userSelectItemFragment;
import kmitl.proj.jittakan58070012.takemehomedemo.model.NewDrivecourse;
import kmitl.proj.jittakan58070012.takemehomedemo.model.userProfile;

/**
 * Created by 58070012 on 11/17/2017.
 */
class userSelectHolder extends RecyclerView.ViewHolder{

    public TextView start;
    public TextView destination;
    public TextView timeanddate;
    public TextView seatLeft;



    public userSelectHolder(View itemView) {
        super(itemView);
        start = itemView.findViewById(R.id.homeShow2);
        destination = itemView.findViewById(R.id.homeDestination2);
        timeanddate = itemView.findViewById(R.id.homeDateAndTime2);
        seatLeft = itemView.findViewById(R.id.seatleft2);

    }
}

public class selectAdapter extends RecyclerView.Adapter<userSelectHolder> {
    private List<userProfile> newDrivecourseList;
    private AppCompatActivity activity;
    private View view;
    public static CardView cardViewsend;
    public static int position;
    int count;


    public selectAdapter(AppCompatActivity activity, List<userProfile> newDrivecourseList) {
        this.activity = activity;
        this.newDrivecourseList = newDrivecourseList;

    }

    @Override
    public userSelectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.display_home_frag2, null, false);
        userSelectHolder allholder = new userSelectHolder(view);
        return allholder;
    }

    @Override
    public void onBindViewHolder(userSelectHolder allholder, final int position) {
        this.count = 0;
        allholder.start.setText(this.newDrivecourseList.get(position).getDriverCourse().get(0).getStart().toString());
        allholder.destination.setText(this.newDrivecourseList.get(position).getDriverCourse().get(0).getDestination());
        allholder.timeanddate.setText("Date : "+this.newDrivecourseList.get(position).getDriverCourse().get(0).getDate() + " Time : " +this.newDrivecourseList.get(position).getDriverCourse().get(0).getTime());
        for (int i = 0 ; i < this.newDrivecourseList.get(position).getDriverCourse().size(); i ++){
            for (int j = 0 ; j < this.newDrivecourseList.get(position).getDriverCourse().get(i).getSeat().size();j++){
                if (this.newDrivecourseList.get(position).getDriverCourse().get(i).getSeat().get(j).getId().equals("") &&
                        this.newDrivecourseList.get(position).getDriverCourse().get(i).getSeat().get(j).getUser().equals("")){
                    count += 1;
                }
            }

        }
        allholder.seatLeft.setText(this.count+" Seat Left");


    }

    @Override
    public int getItemCount() {

        return this.newDrivecourseList.size();
    }

    public void getPosition(int pos){
        this.position = pos;

        FragmentManager fragmentManager = this.activity.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.FragmentContainer, new userSelectItemFragment()).commit();

    }
}
