package kmitl.proj.jittakan58070012.takemehomedemo.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import kmitl.proj.jittakan58070012.takemehomedemo.R;
import kmitl.proj.jittakan58070012.takemehomedemo.fragment.userSelectItemFragment;
import kmitl.proj.jittakan58070012.takemehomedemo.model.NewDrivecourse;
import kmitl.proj.jittakan58070012.takemehomedemo.model.userProfile;

/**
 * Created by 58070012 on 11/17/2017.
 */

class allHolder extends RecyclerView.ViewHolder{

    public TextView start;
    public TextView destination;
    public TextView timeanddate;
    public TextView seatLeft;
    public CardView cardView;
    public Button clicktoview;


    public allHolder(View itemView) {
        super(itemView);
        start = itemView.findViewById(R.id.homeShow);
        destination = itemView.findViewById(R.id.homeDestination);
        timeanddate = itemView.findViewById(R.id.homeDateAndTime);
        seatLeft = itemView.findViewById(R.id.seatleft);
        cardView = itemView.findViewById(R.id.itemviews);
        clicktoview = itemView.findViewById(R.id.pressToViewButton);
    }
}

public class allAdapter extends RecyclerView.Adapter<allHolder>{
    private List<userProfile> newDrivecourseList;
    private AppCompatActivity activity;
    private View view;
    public static CardView cardViewsend;
    public static int position;
    public static userProfile userCheck;
    //private RecyclerView recyclerView;
    int count;


    public allAdapter(AppCompatActivity activity, List<userProfile> newDrivecourseList) {
        this.activity = activity;
        this.newDrivecourseList = newDrivecourseList;
        Log.d("alladapter", "allAdapter: in adapterset");

    }

    @Override
    public allHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.display_home_frag, null, false);
        allHolder allholder = new allHolder(view);

        //this.recyclerView = parent.findViewById(R.id.showItemlistHome);
        return allholder;
    }

    @Override
    public void onBindViewHolder(allHolder allholder, final int position) {
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
                if (this.count == 0){
                    allholder.seatLeft.setText("Empty Seat");
                }else {
                    allholder.seatLeft.setText(this.count+" Seat Left");
                }

            }
            Log.d("check", "onCreateViewHolder: asdasd");
        }


        allholder.clicktoview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPosition(position, newDrivecourseList.get(position));
                Log.d("position", "onClick: "+position);
            }
        });



    }

    @Override
    public int getItemCount() {

        return this.newDrivecourseList.size();
    }

    public void getPosition(int pos, userProfile userProfile){
         this.position = pos;
         this.userCheck = userProfile;

      FragmentManager fragmentManager = this.activity.getSupportFragmentManager();
      fragmentManager.beginTransaction().replace(R.id.FragmentContainer, new userSelectItemFragment()).commit();

    }
}
