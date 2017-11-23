package kmitl.proj.jittakan58070012.takemehomedemo.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import kmitl.proj.jittakan58070012.takemehomedemo.R;
import kmitl.proj.jittakan58070012.takemehomedemo.model.NewDrivecourse;
import kmitl.proj.jittakan58070012.takemehomedemo.model.userProfile;

/**
 * Created by 58070012 on 11/17/2017.
 */
class allHolder extends RecyclerView.ViewHolder{

    public TextView start;
    public TextView destination;
    public TextView timeanddate;


    public allHolder(View itemView) {
        super(itemView);
        start = itemView.findViewById(R.id.StartShow);
        destination = itemView.findViewById(R.id.DestinationShow);
        timeanddate = itemView.findViewById(R.id.DateAndTime);
    }
}

public class allAdapter extends RecyclerView.Adapter<Holder> {
    private List<userProfile>newDrivecourseList;
    private Activity activity;
    private View view;

    public allAdapter(Activity activity, List<userProfile> newDrivecourseList) {
        this.activity = activity;
        this.newDrivecourseList = newDrivecourseList;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.cousedisplay, null, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        holder.start.setText(this.newDrivecourseList.get(position).getDriverCourse().get(0).getStart());
        holder.destination.setText(this.newDrivecourseList.get(position).getDriverCourse().get(0).getDestination());
        holder.timeanddate.setText("Date : "+this.newDrivecourseList.get(position).getDriverCourse().get(0).getDate() + " Time : " +this.newDrivecourseList.get(position).getDriverCourse().get(0).getTime());


    }

    @Override
    public int getItemCount() {

        return this.newDrivecourseList.size();
    }
}
