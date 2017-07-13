package com.example.morningstar.jobsearchassistant;

/**
 * Created by morningstar on 7/12/17.
 */

public class JobApplication {

    private String company, meeting_date, meeting_time, recruiter, email, phone, jobPosition, compensation, notes;
    private int id, status, reminderMeeting, pendingAction, reminderEmail, jobPositionType, compensationType;

    public void setNotes(String notes){
        this.notes = notes;
    }

    public String getNotes(){
        return notes;
    }

    public void setCompensation(String compensation){
        this.compensation = compensation;
    }

    public void setCompensationType(int compensationType){
        this.compensationType = compensationType;
    }

    public String getCompensation(){
        return compensation;
    }

    public int getCompensationType(){
        return compensationType;
    }

    public void setJobPosition (String jobPosition){
        this.jobPosition = jobPosition;
    }

    public void setJobPositionType(int jobPositionType){
        this.jobPositionType = jobPositionType;
    }

    public String getJobPosition(){
        return jobPosition;
    }

    public int getJobPositionType(){
        return jobPositionType;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getPhone(){
        return phone;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setRecruiter(String recruiter){
        this.recruiter = recruiter;
    }

    public String getRecruiter(){
        return recruiter;
    }

    public int getReminderEmail(){
        return reminderEmail;
    }

    public void setReminderEmail(int reminderEmail){
        this.reminderEmail = reminderEmail;
    }

    public int getPendingAction(){
        return pendingAction;
    }

    public void setPendingAction(int pendingAction){
        this.pendingAction = pendingAction;
    }

    public int getReminderMeeting() {
        return reminderMeeting;
    }

    public void setReminderMeeting(int reminderMeeting) {
        this.reminderMeeting = reminderMeeting;
    }

    public JobApplication(String company)
    {
        this.company = company;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMeetingDate() {
        return meeting_date;
    }

    public void setMeetingDate(String date) {
        this.meeting_date = date;
    }

    public String getMeetingTime() {
        return meeting_time;
    }

    public void setMeetingTime(String time) {
        this.meeting_time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
