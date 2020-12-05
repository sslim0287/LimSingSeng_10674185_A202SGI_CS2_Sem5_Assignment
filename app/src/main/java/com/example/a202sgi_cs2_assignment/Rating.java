package com.example.a202sgi_cs2_assignment;

public class Rating{

    public String rating, comment,username, studentID;

    public Rating(){

    }


    public Rating(String rating, String comment, String username,String studentID){
        this.rating = rating;
        this.comment = comment;
        this.username = username;
        this.studentID = studentID;

    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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
