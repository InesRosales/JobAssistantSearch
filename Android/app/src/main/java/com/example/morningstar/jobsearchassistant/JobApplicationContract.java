package com.example.morningstar.jobsearchassistant;

import android.provider.BaseColumns;

/**
 * Created by morningstar on 6/29/17.
 */

public class JobApplicationContract {


    public static final class JobApplication implements BaseColumns{
        public static final String TABLE_NAME = "jobApplication";
        public static final String COLUMN_COMPANY= "company";
        public static final String COLUMN_JOB_STATUS = "jobStatus";
        public static final String COLUMN_PENDING_ACTION = "pendingAction";
        public static final String COLUMN_RECRUITER = "recruiter";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PHONE = "phone";
        public static final String COLUMN_JOB_POSITION = "jobPosition";
        public static final String COLUMN_JOB_POSITION_TYPE = "employmentType";
        public static final String COLUMN_COMPENSATION = "compensation";
        public static final String COLUMN_COMPENSATION_TYPE = "compensationType";
        public static final String COLUMN_NOTES = "notes";
        public static final String COLUMN_TIMESTAMP = "timestamp";
        public static final String COLUMN_DATE_MEETING = "dateMeeting";
        public static final String COLUMN_REMINDER_EMAIL = "reminderEmail";
        public static final String COLUMN_REMINDER_MEETING = "reminderMeeting";
    }
}
