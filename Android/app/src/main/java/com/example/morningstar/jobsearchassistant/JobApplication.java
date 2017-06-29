package com.example.morningstar.jobsearchassistant;

/**
 * Created by morningstar on 6/29/17.
 */

public class JobApplication {

    private String company;
    private int jobStatus;
    private int pendingAction;
    private String recruiterName;
    private String email;
    private String phone;
    private String jobPosition;
    private int employmentType;
    private String compensation;
    private int compensationType;
    private String notes;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(int jobStatus) {
        this.jobStatus = jobStatus;
    }

    public int getPendingAction() {
        return pendingAction;
    }

    public void setPendingAction(int pendingAction) {
        this.pendingAction = pendingAction;
    }

    public String getRecruiterName() {
        return recruiterName;
    }

    public void setRecruiterName(String recruiterName) {
        this.recruiterName = recruiterName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public int getEmploymentType() {
        return employmentType;
    }

    public void setEmploymentType(int employmentType) {
        this.employmentType = employmentType;
    }

    public String getCompensation() {
        return compensation;
    }

    public void setCompensation(String compensation) {
        this.compensation = compensation;
    }

    public int getCompensationType() {
        return compensationType;
    }

    public void setCompensationType(int compensationType) {
        this.compensationType = compensationType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
