package com.example.morningstar.jobsearchassistant;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import 	java.util.Calendar;
import java.util.Locale;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class JobApplicationFormActivity extends AppCompatActivity {

    TextView tv_date, tv_time;
    EditText et_company, et_recruiter, et_email, et_phone, et_jobPosition, et_compensation, et_notes;
    Spinner s_status, s_pendingAction, s_employmentType, s_compensationType, s_reminderEmail, s_reminderMeeting;
    LinearLayout ll_meeting, ll_emailReminder;
    Calendar mCalendar;
    SQLiteDatabase mDb;
    Toast mToast;

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mCalendar.set(Calendar.YEAR, year);
            mCalendar.set(Calendar.MONTH, monthOfYear);
            mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateMeetingDateLabel();
        }

    };

    TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener(){

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            mCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            mCalendar.set(Calendar.MINUTE, minute);
            updateMeetingTimeLabel();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_application_form);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        JobApplicationDbHelper dbHelper = new JobApplicationDbHelper(this);
        mDb = dbHelper.getWritableDatabase();

        mCalendar = Calendar.getInstance();

        tv_date = (TextView) findViewById(R.id.textview_date);
        tv_time = (TextView) findViewById(R.id.textview_time);
        s_pendingAction = (Spinner) findViewById(R.id.spinner_pending_action);
        s_status = (Spinner) findViewById(R.id.spinner_status);
        et_company = (EditText) findViewById(R.id.edittext_company);
        et_recruiter = (EditText) findViewById(R.id.edittext_recruiter_name);
        et_email = (EditText) findViewById(R.id.edittext_email);
        et_phone = (EditText) findViewById(R.id.edittext_phone);
        et_jobPosition = (EditText) findViewById(R.id.edittext_job_position);
        s_employmentType = (Spinner) findViewById(R.id.spinner_employment_type);
        et_compensation = (EditText) findViewById(R.id.edittext_compensation);
        s_compensationType = (Spinner) findViewById(R.id.spinner_compensation_type);
        et_notes = (EditText) findViewById(R.id.edittext_notes);
        ll_meeting = (LinearLayout) findViewById(R.id.linearlayout_meeting);
        ll_emailReminder= (LinearLayout) findViewById(R.id.linearlayout_email_reminder);
        s_reminderEmail = (Spinner) findViewById(R.id.spinner_reminder_email);
        s_reminderMeeting = (Spinner) findViewById(R.id.spinner_reminder_meeting);

        s_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 4 || position == 5){
                    ll_meeting.setVisibility(View.VISIBLE);
                }
                else{
                    ll_meeting.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        s_pendingAction.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 1){
                    ll_emailReminder.setVisibility(View.VISIBLE);
                }
                else {
                    ll_emailReminder.setVisibility(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        tv_date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(JobApplicationFormActivity.this, date, mCalendar
                        .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                        mCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        tv_time.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new TimePickerDialog(JobApplicationFormActivity.this, time,
                        mCalendar.get(Calendar.HOUR_OF_DAY),
                        mCalendar.get(Calendar.MINUTE),
                        false).show();
            }
        });


    }

    private void updateMeetingTimeLabel(){

        String myFormat = "h:mm a";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        tv_time.setText(sdf.format(mCalendar.getTime()));

    }

    private void updateMeetingDateLabel() {

        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tv_date.setText(sdf.format(mCalendar.getTime()));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_job_application_form, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
                String company = et_company.getText().toString().trim();
                if(company.equals("")) {
                    if(mToast != null)
                        mToast.cancel();

                    mToast = Toast.makeText(this, this.getString(R.string.form_error_company), Toast.LENGTH_LONG);
                    mToast.show();
                    
                }
                else {
                    ContentValues cv = new ContentValues();
                    cv.put(JobApplicationContract.JobApplication.COLUMN_COMPANY, company);

                    int status = s_status.getSelectedItemPosition();
                    cv.put(JobApplicationContract.JobApplication.COLUMN_JOB_STATUS, status);

                    int pendingAction = s_pendingAction.getSelectedItemPosition();
                    cv.put(JobApplicationContract.JobApplication.COLUMN_PENDING_ACTION, pendingAction);

                    if(ll_meeting.isShown()) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:MM");
                        String date = sdf.format(mCalendar.getTime());

                        cv.put(JobApplicationContract.JobApplication.COLUMN_DATE_MEETING, date);
                    }

                    String recruiter = et_recruiter.getText().toString().trim();
                    cv.put(JobApplicationContract.JobApplication.COLUMN_RECRUITER, recruiter);

                    String email = et_email.getText().toString().trim();
                    cv.put(JobApplicationContract.JobApplication.COLUMN_EMAIL, email);

                    String phone = et_phone.getText().toString().trim();
                    cv.put(JobApplicationContract.JobApplication.COLUMN_PHONE, phone);

                    String jobPosition = et_jobPosition.getText().toString().trim();
                    cv.put(JobApplicationContract.JobApplication.COLUMN_JOB_POSITION, jobPosition);

                    int employmentType = s_employmentType.getSelectedItemPosition();
                    cv.put(JobApplicationContract.JobApplication.COLUMN_EMPLOYMENT_TYPE, employmentType);

                    String compensation = et_compensation.getText().toString().trim();
                    cv.put(JobApplicationContract.JobApplication.COLUMN_COMPENSATION, compensation);

                    int compensationType = s_compensationType.getSelectedItemPosition();
                    cv.put(JobApplicationContract.JobApplication.COLUMN_COMPENSATION_TYPE, compensationType);

                    String notes = et_notes.getText().toString().trim();
                    cv.put(JobApplicationContract.JobApplication.COLUMN_NOTES, notes);

                    int reminderMeeting = s_reminderMeeting.getSelectedItemPosition();
                    cv.put(JobApplicationContract.JobApplication.COLUMN_REMINDER_MEETING, reminderMeeting);

                    int reminderEmail = s_reminderEmail.getSelectedItemPosition();
                    cv.put(JobApplicationContract.JobApplication.COLUMN_REMINDER_EMAIL, reminderEmail);

                    mDb.insert(JobApplicationContract.JobApplication.TABLE_NAME, null, cv);

                    Intent data = new Intent();
                    String text = "Result to be returned....";
                    data.setData(Uri.parse(text));
                    setResult(RESULT_OK, data);
                    finish();

                }
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }
}

