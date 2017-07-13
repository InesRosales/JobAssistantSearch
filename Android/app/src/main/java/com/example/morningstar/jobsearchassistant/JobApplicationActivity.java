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
    TextView
            tv_email, tv_phone, tv_jobPosition, tv_jobPositionType, tv_compensation, tv_compensationType, tv_notes;
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

            TextView tv_company = (TextView) findViewById(R.id.job_et_company);
            tv_company.setText(company);

            String[] array = getResources().getStringArray(R.array.form_status_array);
            TextView tv_status = (TextView) findViewById(R.id.job_tv_status);
            tv_status.setText(array[status]);

            LinearLayout ll_pendingAction = (LinearLayout) findViewById(R.id.job_ll_pendingAction);

            if(pendingAction != 0) {

                TextView tv_pendingAction = (TextView) findViewById(R.id.job_tv_pendingAction);
                array = getResources().getStringArray(R.array.form_pending_action_array);
                tv_pendingAction.setText(array[pendingAction]);

                if(reminderEmail != 0) {
                    array = getResources().getStringArray(R.array.form_reminder_array);
                    TextView tv_reminderEmail = (TextView) findViewById(R.id.job_tv_reminderEmail);
                    tv_reminderEmail.setText(array[reminderEmail]);

                    LinearLayout ll_reminderEmail = (LinearLayout) findViewById(R.id.job_ll_reminderEmail);
                    ll_reminderEmail.setVisibility(View.VISIBLE);
                }

            }
            else{
                ll_pendingAction.setVisibility(View.GONE);
            }

            if (status == 4 || status == 5) {

                Calendar calendar = Calendar.getInstance();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Util.DATE_HOUR_FORMAT);
                try {
                    calendar.setTime(simpleDateFormat.parse(date));

                    LinearLayout ll_reminderMeeting = (LinearLayout) findViewById(R.id.job_ll_meeting);
                    ll_reminderMeeting.setVisibility(View.VISIBLE);

                    TextView tv_date = (TextView) findViewById(R.id.job_tv_date);
                    TextView tv_time = (TextView) findViewById(R.id.job_tv_time);

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

                    TextView tv_reminderMeeting = (TextView) findViewById(R.id.job_tv_reminderMeeting);
                    array = getResources().getStringArray(R.array.form_reminder_array);
                    tv_reminderMeeting.setText(array[reminderMeeting]);

                } catch (ParseException e) {
                    //TODO handle error
                }
            }

            handleEmptyString(recruiter, R.id.job_ll_recruiter, R.id.job_tv_recruiter);
            handleEmptyString(email, R.id.job_ll_email, R.id.job_tv_email);
            handleEmptyString(phone, R.id.job_ll_phone, R.id.job_tv_phone);

            handleEmptyStringContainingArray(jobPosition, R.id.job_ll_jobPosition, R.id.job_tv_jobPosition, R.id.job_tv_jobPositionType, R.array.form_job_position_type_array, jobPositionType);
            handleEmptyStringContainingArray(compensation, R.id.job_ll_compensation, R.id.job_tv_compensation, R.id.job_tv_compensationType, R.array.form_compensation_type_array, compensationType );

            handleEmptyString(notes, R.id.job_ll_notes, R.id.job_tv_notes);

        }
        else {
            //TODO error
        }
  }

    public void handleEmptyString (String text, int resource_LinearLayout, int resourceTextView)
    {
        if(text.equals("")){
            LinearLayout linearLayout = (LinearLayout) findViewById(resource_LinearLayout);
            linearLayout.setVisibility(View.GONE);
        }else{
            TextView textView = (TextView) findViewById(resourceTextView);
            textView.setText(text);
        }
    }

    public void handleEmptyStringContainingArray(String text, int resourceLinearLayout, int resourceTextView, int resourceTextViewArray, int resourceArray, int pos){
        if(text.equals("")){
            LinearLayout linearLayout = (LinearLayout) findViewById(resourceLinearLayout);
            linearLayout.setVisibility(View.GONE);
        } else {
            TextView textView = (TextView) findViewById(resourceTextView);
            TextView arrayTextView = (TextView) findViewById(resourceTextViewArray);
            textView.setText(text);

            String [] array = getResources().getStringArray(resourceArray);
            arrayTextView.setText(array[pos]);
        }
    }
}
