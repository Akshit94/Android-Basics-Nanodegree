package com.example.jainsaab.reportcard;

public class ReportCard {

    private String mCourse;
    private String mSubject;
    private String mGrade;
    private int mYear;

    public ReportCard(String course, String subject, String grade, int year){
        mCourse = course;
        mSubject = subject;
        mGrade = grade;
        mYear = year;
    }

    @Override
    public String toString(){
        return "Course: " + mCourse + '\n'
                + "Year: " + mYear + '\n'
                + "Subject: " + mSubject + '\n'
                + "Grade: " + mGrade;
    }

    public String getCourse(){
        return mCourse;
    }

    public void setCourse(String course){
        mCourse = course;
    }

    public String getSubject(){
        return mSubject;
    }

    public void setSubject(String subject){
        mSubject = subject;
    }

    public String getGrade() {
        return mGrade;
    }

    public void setGrade(String grade) {
        mGrade = grade;
    }

    public int getYear() {
        return mYear;
    }

    public void setYear(int year) {
        mYear = year;
    }

    public void setGradeUsingMarks(int marks){
        if(marks <= 100 && marks >= 85){
            mGrade = "A";
        } else if (marks <= 85 && marks >= 70){
            mGrade = "B";
        } else if (marks <= 70 && marks >= 55){
            mGrade = "C";
        }  else if (marks <= 55 && marks >= 40){
            mGrade = "D";
        } else {
            mGrade = "F";
        }
    }

    public String getRemarksForGrade(){
        String remarks;
        switch (mGrade){
            case "A":
                remarks = "Excellent! Passed with flying colors.";
                break;
            case "B":
                remarks = "Good Job! Could have done better.";
                break;
            case "C":
                remarks = "Nice try! But needs more practice.";
                break;
            case "D":
                remarks = "Should study and practice hard.";
                break;
            case "F":
                remarks = "Fail!";
                break;
            default:
                remarks = "No Remarks.";

        }
        return remarks;
    }
}
