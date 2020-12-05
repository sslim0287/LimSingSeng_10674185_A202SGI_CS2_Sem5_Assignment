package com.example.a202sgi_cs2_assignment;

public class Booking {

    public String date, time, room;
    public String username, studentID;

    public Booking(){

    }

    public Booking(String date, String time, String room,String username,String studentID){
        this.date = date;
        this.time = time;
        this.room = room;
        this.username = username;
        this.studentID = studentID;

    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
}
