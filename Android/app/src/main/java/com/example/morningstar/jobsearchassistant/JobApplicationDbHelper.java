package com.example.morningstar.jobsearchassistant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by morningstar on 7/4/17.
 */

public class JobApplicationDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "jobApplication.db";
    private static final int DATABASE_VERSION = 1;

    public JobApplicationDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION); //TODO the null has to do with the cursor, so there will be changes here
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_TABLE_JOB_APPLICATION = "CREATE TABLE "+
                JobApplicationContract.JobApplication.TABLE_NAME + " ("+
                JobApplicationContract.JobApplication._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                JobApplicationContract.JobApplication.COLUMN_COMPANY + " TEXT NOT NULL, "+
                JobApplicationContract.JobApplication.COLUMN_JOB_STATUS + " INTEGER NOT NULL, "+
                JobApplicationContract.JobApplication.COLUMN_PENDING_ACTION + " INTEGER NOT NULL, "+
                JobApplicationContract.JobApplication.COLUMN_RECRUITER + " TEXT, "+
                JobApplicationContract.JobApplication.COLUMN_EMAIL +" TEXT, "+
                JobApplicationContract.JobApplication.COLUMN_PHONE +" TEXT, "+
                JobApplicationContract.JobApplication.COLUMN_JOB_POSITION +" TEXT, "+
                JobApplicationContract.JobApplication.COLUMN_EMPLOYMENT_TYPE+" INTEGER, "+
                JobApplicationContract.JobApplication.COLUMN_COMPENSATION+" INTEGER, "+
                JobApplicationContract.JobApplication.COLUMN_COMPENSATION_TYPE+" INTEGER NOT NULL, "+
                JobApplicationContract.JobApplication.COLUMN_NOTES+" TEXT, "+
                JobApplicationContract.JobApplication.COLUMN_TIMESTAMP+" TIMESTAMP DEFAULT CURRENT_TIMESTAMP"+
                JobApplicationContract.JobApplication.COLUMN_DATE_MEETING+" TIMESTAMP"+
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_JOB_APPLICATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int j){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + JobApplicationContract.JobApplication.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

}
