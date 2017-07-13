package com.example.morningstar.jobsearchassistant;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JobApplicationActivity extends AppCompatActivity {

    int id;
    TextView tv_company, tv_status, tv_date, tv_time, tv_reminderMeeting, tv_pendingAction, tv_reminderEmail,
            tv_recruiter, tv_email, tv_phone, tv_jobPosition, tv_jobPositionType, tv_compensation, tv_compensationType, tv_notes;
    LinearLayout ll_reminderMeeting, ll_reminderEmail;
    SQLiteDatabase mDb;
    JobApplication job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_application);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        JobApplicationDbHelper dbHelper = new JobApplicationDbHelper(this);
        mDb = dbHelper.getWritableDatabase();

        String [] args = {Integer.toString(id)};
        Cursor cursor = mDb.rawQuery("SELECT * FROM " +
                JobApplicationContract.JobApplication.TABLE_NAME+" WHERE "+
                JobApplicationContract.JobApplication._ID+" = ?", args);

        if(cursor != null) {
            cursor.moveToFirst();

            String company = cursor.getString(cursor.getColumnIndex(JobApplicationContract.JobApplication.COLUMN_COMPANY));
            int status = cursor.getInt(cursor.getColumnIndex(JobApplicationContract.JobApplication.COLUMN_JOB_STATUS));
            String date = cursor.getString(cursor.getColumnIndex(JobApplicationContract.JobApplication.COLUMN_DATE_MEETING));
            int reminderMeeting = cursor.getInt(cursor.getColumnIndex(JobApplicationContract.JobApplication.COLUMN_REMINDER_MEETING));
            int pendingAction = cursor.getInt(cursor.getColumnIndex(JobApplicationContract.JobApplication.COLUMN_PENDING_ACTION));
            int reminderEmail = cursor.getInt(cursor.getColumnIndex(JobApplicationContract.JobApplication.COLUMN_REMINDER_EMAIL));
            String recruiter = cursor.getString(cursor.getColumnIndex(JobApplicationContract.JobApplication.COLUMN_RECRUITER));
            String email = cursor.getString(cursor.getColumnIndex(JobApplicationContract.JobApplication.COLUMN_EMAIL));
            String phone = cursor.getString(cursor.getColumnIndex(JobApplicationContract.JobApplication.COLUMN_PHONE));
            String jobPosition = cursor.getString(cursor.getColumnIndex(JobApplicationContract.JobApplication.COLUMN_JOB_POSITION));
            int jobPositionType = cursor.getInt(cursor.getColumnIndex(JobApplicationContract.JobApplication.COLUMN_JOB_POSITION_TYPE));
            String compensation = cursor.getString(cursor.getColumnIndex(JobApplicationContract.JobApplication.COLUMN_COMPENSATION));
            int compensationType = cursor.getInt(cursor.getColumnIndex(JobApplicationContract.JobApplication.COLUMN_COMPENSATION_TYPE));
            String notes = cursor.getString(cursor.getColumnIndex(JobApplicationContract.JobApplication.COLUMN_NOTES));

            job = new JobApplication(company);
            job.setStatus(status);
            job.setReminderMeeting(reminderMeeting);
            job.setPendingAction(pendingAction);
            job.setReminderEmail(reminderEmail);
            job.setRecruiter(recruiter);
            job.setEmail(email);
            job.setPhone(phone);
            job.setJobPosition(jobPosition);
            job.setJobPositionType(jobPositionType);
            job.setCompensation(compensation);
            job.setCompensationType(compensationType);
            job.setNotes(notes);

            tv_company = (TextView) findViewById(R.id.job_et_company);
            tv_company.setText(company);

            String[] array = getResources().getStringArray(R.array.form_status_array);
            tv_status = (TextView) findViewById(R.id.job_tv_status);
            tv_status.setText(array[status]);

            if (status == 4 || status == 5) {

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Util.DATE_HOUR_FORMAT);
                try {
                    calendar.setTime(simpleDateFormat.parse(date));

                    ll_reminderMeeting = (LinearLayout) findViewById(R.id.job_ll_meeting);
                    ll_reminderMeeting.setVisibility(View.VISIBLE);

                    tv_date = (TextView) findViewById(R.id.job_tv_date);
                    tv_time = (TextView) findViewById(R.id.job_tv_time);

                    String ampm;
                    int amPm = calendar.get(Calendar.AM_PM);
                    if (amPm == Calendar.PM)
                        ampm = "PM";
                    else
                        ampm = "AM";

                    int month = calendar.get(Calendar.MONTH) + 1;

                    String meeting_date = Util.fillWithZero(calendar.get(Calendar.DAY_OF_MONTH)) + " / " +
                            Util.fillWithZero(month) + " / " +
                            calendar.get(Calendar.YEAR);

                    job.setMeetingDate(meeting_date);
                    tv_date.setText(meeting_date);

                    String meeting_time = Util.fillWithZero(calendar.get(Calendar.HOUR)) + " : " +
                            Util.fillWithZero(calendar.get(Calendar.MINUTE)) + " " + ampm;

                    job.setMeetingTime(meeting_time);
                    tv_time.setText(meeting_time);
                } catch (ParseException e) {
                    //TODO handle error
                }
            }



            array = getResources().getStringArray(R.array.form_pending_action_array);
            tv_pendingAction = (TextView) findViewById(R.id.job_tv_pendingAction);
            tv_pendingAction.setText(array[pendingAction]);

            tv_reminderMeeting = (TextView) findViewById(R.id.job_tv_reminderMeeting);
            array = getResources().getStringArray(R.array.form_reminder_array);
            tv_reminderMeeting.setText(array[reminderMeeting]);

            if(reminderEmail == 1) {
                tv_reminderEmail = (TextView) findViewById(R.id.job_tv_reminderEmail);
                tv_reminderEmail.setText(array[reminderEmail]);

                ll_reminderEmail.setVisibility(View.VISIBLE);
            }

            tv_recruiter = (TextView) findViewById(R.id.job_tv_recruiter);
            tv_recruiter.setText(recruiter);

            tv_email = (TextView) findViewById(R.id.job_tv_email);
            tv_email.setText(email);

            tv_phone = (TextView) findViewById(R.id.job_tv_phone);
            tv_phone.setText(phone);

            tv_jobPosition = (TextView) findViewById(R.id.job_tv_jobPosition);
            tv_jobPositionType = (TextView) findViewById(R.id.job_tv_jobPositionType);
            tv_jobPosition.setText(jobPosition);
            array = getResources().getStringArray(R.array.form_job_position_type_array);
            tv_jobPositionType.setText(array[jobPositionType]);

            tv_compensation = (TextView) findViewById(R.id.job_tv_compensation);
            tv_compensationType = (TextView) findViewById(R.id.job_tv_compensationType);
            array = getResources().getStringArray(R.array.form_compensation_variations);
            tv_compensation.setText(compensation);
            tv_compensationType.setText(array[compensationType]);

            tv_notes = (TextView) findViewById(R.id.job_tv_notes);
            tv_notes.setText(notes);

        }
        else {
            //TODO error
        }






    }
}
