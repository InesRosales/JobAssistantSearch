package com.example.morningstar.jobsearchassistant;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by morningstar on 7/4/17.
 */

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.JobViewHolder> {

    final private JobListListener mOnClickListener;
    private int numberOfJobs;
    private int viewHolderCount = 0;

    public interface JobListListener{
        void onJobItemClick(int position);
    }

    public JobListAdapter(int numberOfItems, JobListListener jobListListener){
        numberOfJobs = numberOfItems;
        mOnClickListener = jobListListener;
    }

    @Override
    public int getItemCount(){
        return numberOfJobs;
    }

    @Override
    public void onBindViewHolder(JobViewHolder jobViewHolder, int position){
        jobViewHolder.bind(position);
    }

    @Override
    public JobViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        Context context = viewGroup.getContext();
        int layoutIdForJobListItem = R.layout.job_list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(layoutIdForJobListItem, viewGroup, false);
        JobViewHolder jobViewHolder = new JobViewHolder(view);

        jobViewHolder.tv_item.setText("pos"+viewHolderCount);
        System.out.println(viewHolderCount);
        viewHolderCount++;

        return jobViewHolder;
    }


    class JobViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_item;

        public JobViewHolder(View itemView){
            super(itemView);
            tv_item = (TextView) itemView.findViewById(R.id.tv_job_list_item);
            itemView.setOnClickListener(this);

        }

        public void bind (int pos){
           tv_item.setText(String.valueOf(pos));
        }

        public void onClick(View view){
            int position = getAdapterPosition();
            mOnClickListener.onJobItemClick(position);
        }
    }
}
