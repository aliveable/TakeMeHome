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

/**
 * Created by 58070012 on 11/17/2017.
 */
class  Holder extends RecyclerView.ViewHolder{

    public TextView start;
    public TextView destination;
    public TextView timeanddate;


    public Holder(View itemView) {
        super(itemView);
        start = itemView.findViewById(R.id.StartShow);
        destination = itemView.findViewById(R.id.DestinationShow);
        timeanddate = itemView.findViewById(R.id.DateAndTime);
    }
}

public class dataAdapter extends RecyclerView.Adapter<Holder> {
    private List<NewDrivecourse>newDrivecourseList;
    private Activity activity;
    private View view;

    public dataAdapter(Activity activity, List<NewDrivecourse> newDrivecourseList) {
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
        holder.start.setText(this.newDrivecourseList.get(position).getStart());
        holder.destination.setText(this.newDrivecourseList.get(position).getDestination());
        holder.timeanddate.setText(this.newDrivecourseList.get(position).getDate() + "  " + this.newDrivecourseList.get(position).getTime());

        Log.d("CheckOutput", "onBindViewHolder: " + this.newDrivecourseList.get(position).getStart() +" "
                +  this.newDrivecourseList.get(position).getDestination() + " " + this.newDrivecourseList.get(position).getDate() + "  " + this.newDrivecourseList.get(position).getTime());
    }

    @Override
    public int getItemCount() {

        return this.newDrivecourseList.size();
    }
}
