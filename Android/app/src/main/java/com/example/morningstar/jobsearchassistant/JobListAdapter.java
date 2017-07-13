package com.example.morningstar.jobsearchassistant;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by morningstar on 7/4/17.
 */

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.JobViewHolder> {

    final private JobListListener mOnClickListener;
    private Cursor cursor;

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
        jobViewHolder.id = cursor.getInt(cursor.getColumnIndex(JobApplicationContract.JobApplication._ID));
        String company = cursor.getString(cursor.getColumnIndex(JobApplicationContract.JobApplication.COLUMN_COMPANY));
        int status = cursor.getInt(cursor.getColumnIndex(JobApplicationContract.JobApplication.COLUMN_JOB_STATUS));
        jobViewHolder.bind(company, status);
    }

    @Override
    public JobViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        Context context = viewGroup.getContext();
        int layoutIdForJobListItem = R.layout.job_list_item;
        LayoutInflater layoutInflater = LayoutInflater.from(context);

        View view = layoutInflater.inflate(layoutIdForJobListItem, viewGroup, false);
        JobViewHolder jobViewHolder = new JobViewHolder(view);

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
        TextView tv_status;
        int id;

        public JobViewHolder(View itemView){
            super(itemView);
            tv_item = (TextView) itemView.findViewById(R.id.jobListItem_tv_company);
            tv_status = (TextView) itemView.findViewById(R.id.jobListItem_tv_status);
            itemView.setOnClickListener(this);

        }

        public void bind (String s, int status){
            tv_item.setText(s);
            String [] statusArray = itemView.getResources().getStringArray(R.array.form_status_array);
            tv_status.setText(statusArray[status]);

        }

        public void onClick(View view){
            int position = getAdapterPosition();
            mOnClickListener.onJobItemClick(position);

            Context context = view.getContext();
            Intent newJobActivity = new Intent(context, JobApplicationActivity.class);
            newJobActivity.putExtra("id", id);
            context.startActivity(newJobActivity);


        }
    }
}
