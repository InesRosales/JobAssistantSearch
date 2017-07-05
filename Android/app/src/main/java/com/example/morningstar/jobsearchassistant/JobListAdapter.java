package com.example.morningstar.jobsearchassistant;

import android.content.Context;
import android.database.Cursor;
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
    private Cursor cursor;
    private int viewHolderCount = 0;

    public interface JobListListener{
        void onJobItemClick(int position);
    }

    public JobListAdapter(JobListListener jobListListener, Cursor c){
        cursor = c;
        mOnClickListener = jobListListener;
    }

    @Override
    public int getItemCount(){
        return cursor.getCount();
    }

    @Override
    public void onBindViewHolder(JobViewHolder jobViewHolder, int position){
        if(!cursor.moveToPosition(position))
            return;

        String company = cursor.getString(cursor.getColumnIndex(JobApplicationContract.JobApplication.COLUMN_COMPANY));

        jobViewHolder.bind(company);
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

    public void swapCursor(Cursor newCursor) {
        if (newCursor != null) {
            cursor = newCursor;
            notifyDataSetChanged();
        }
    }


    class JobViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_item;

        public JobViewHolder(View itemView){
            super(itemView);
            tv_item = (TextView) itemView.findViewById(R.id.tv_job_list_item);
            itemView.setOnClickListener(this);

        }

        public void bind (String s){
           tv_item.setText(s);
        }

        public void onClick(View view){
            int position = getAdapterPosition();
            mOnClickListener.onJobItemClick(position);
        }
    }
}
