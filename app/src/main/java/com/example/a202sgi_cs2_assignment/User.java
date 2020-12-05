package com.example.a202sgi_cs2_assignment;

public class User {

    public String fullName, phoneNum, studentID, email;

    public User(){

    }

    public User(String fullName, String phoneNum, String studentID, String email){
        this.fullName=fullName;
        this.phoneNum=phoneNum;
        this.studentID=studentID;
        this.email=email;


    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
