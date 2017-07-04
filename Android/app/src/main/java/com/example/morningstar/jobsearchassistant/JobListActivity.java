package com.example.morningstar.jobsearchassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class JobListActivity extends AppCompatActivity implements  JobListAdapter.JobListListener{

    private static int NUM_JOBS = 20;
    private JobListAdapter mAdapter;
    private RecyclerView mJobList;
    Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newFormActivity = new Intent(JobListActivity.this, JobApplicationFormActivity.class);
                startActivity(newFormActivity);
            }
        });


        mJobList = (RecyclerView) findViewById(R.id.rv_job_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mJobList.setLayoutManager(layoutManager);
        mJobList.setHasFixedSize(true); //TODO change this
        mAdapter = new JobListAdapter(NUM_JOBS, this);
        mJobList.setAdapter(mAdapter);
    }

    @Override
    public void onJobItemClick(int position) {
        if(mToast != null)
            mToast.cancel();

        mToast = Toast.makeText(this, "Pos"+position, Toast.LENGTH_LONG);
        mToast.show();
    }
}