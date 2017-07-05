package com.example.morningstar.jobsearchassistant;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by morningstar on 7/4/17.
 */

public class Util {

    static public void insertFakeData(SQLiteDatabase db){
        if(db == null)
            return;

        List<ContentValues> list = new ArrayList<ContentValues>();

        ContentValues testValues = new ContentValues();
        testValues.put(JobApplicationContract.JobApplication.COLUMN_COMPANY, "Company 1");
        testValues.put(JobApplicationContract.JobApplication.COLUMN_JOB_STATUS, 1);
        testValues.put(JobApplicationContract.JobApplication.COLUMN_PENDING_ACTION, 1);
        testValues.put(JobApplicationContract.JobApplication.COLUMN_COMPENSATION_TYPE, 1);
        list.add(testValues);

        testValues = new ContentValues();
        testValues.put(JobApplicationContract.JobApplication.COLUMN_COMPANY, "Company 2");
        testValues.put(JobApplicationContract.JobApplication.COLUMN_JOB_STATUS, 1);
        testValues.put(JobApplicationContract.JobApplication.COLUMN_PENDING_ACTION, 1);
        testValues.put(JobApplicationContract.JobApplication.COLUMN_COMPENSATION_TYPE, 1);
        list.add(testValues);

        testValues = new ContentValues();
        testValues.put(JobApplicationContract.JobApplication.COLUMN_COMPANY, "Company 3");
        testValues.put(JobApplicationContract.JobApplication.COLUMN_JOB_STATUS, 1);
        testValues.put(JobApplicationContract.JobApplication.COLUMN_PENDING_ACTION, 1);
        testValues.put(JobApplicationContract.JobApplication.COLUMN_COMPENSATION_TYPE, 1);
        list.add(testValues);

        testValues = new ContentValues();
        testValues.put(JobApplicationContract.JobApplication.COLUMN_COMPANY, "Company 4");
        testValues.put(JobApplicationContract.JobApplication.COLUMN_JOB_STATUS, 1);
        testValues.put(JobApplicationContract.JobApplication.COLUMN_PENDING_ACTION, 1);
        testValues.put(JobApplicationContract.JobApplication.COLUMN_COMPENSATION_TYPE, 1);
        list.add(testValues);

        try{
            db.beginTransaction();
            db.delete(JobApplicationContract.JobApplication.TABLE_NAME, null, null);
            for(ContentValues c : list){
                db.insert(JobApplicationContract.JobApplication.TABLE_NAME, null, c);
            }
            db.setTransactionSuccessful();
        } catch (SQLException e){
            System.out.println("Something went really wrong");
        } finally {
            db.endTransaction();
        }

    }
}
