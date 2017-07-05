package com.example.morningstar.jobsearchassistant;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class JobListActivity extends AppCompatActivity implements  JobListAdapter.JobListListener{

    static final int ADD_ITEM_REQUEST = 1;
    private JobListAdapter mAdapter;
    private RecyclerView mJobList;

    private SQLiteDatabase mDb;
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
                startActivityForResult(newFormActivity, ADD_ITEM_REQUEST);
            }
        });

        JobApplicationDbHelper dbHelper = new JobApplicationDbHelper(this);
        mDb = dbHelper.getWritableDatabase();
        Cursor cursor = getAllJobs();

        mJobList = (RecyclerView) findViewById(R.id.rv_job_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mJobList.setLayoutManager(layoutManager);
        mJobList.setHasFixedSize(true); //TODO change this
        mAdapter = new JobListAdapter(this, cursor);
        mJobList.setAdapter(mAdapter);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_ITEM_REQUEST) {
            if (resultCode == RESULT_OK) {
                mAdapter.swapCursor(getAllJobs());
            }
        }
    }

    private Cursor getAllJobs(){
        return mDb.query(
                JobApplicationContract.JobApplication.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                JobApplicationContract.JobApplication.COLUMN_TIMESTAMP);
    }

    @Override
    public void onJobItemClick(int position) {
        if(mToast != null)
            mToast.cancel();

        mToast = Toast.makeText(this, "Pos"+position, Toast.LENGTH_LONG);
        mToast.show();
    }
}