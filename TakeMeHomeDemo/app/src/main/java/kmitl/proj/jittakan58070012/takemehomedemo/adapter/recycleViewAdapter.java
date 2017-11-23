package kmitl.proj.jittakan58070012.takemehomedemo.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kmitl.proj.jittakan58070012.takemehomedemo.R;

/**
 * Created by 58070012 on 11/23/2017.
 */

public class recycleViewAdapter extends RecyclerView.Adapter<recycleViewAdapter.recycleViewHolder> {
    int seatleft;

    public recycleViewAdapter(int left) {
        this.seatleft = left;
    }

    @Override
    public recycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(recycleViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class recycleViewHolder extends RecyclerView.ViewHolder {
        TextView seatleft;


        public recycleViewHolder(View v) {
            super(v);
            seatleft = (TextView) v.findViewById(R.id.seatleft);

        }
    }

}
