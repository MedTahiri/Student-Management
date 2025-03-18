package com.mohamed.tahiri.studentmanagement.models;

public class createStudent{
    public String firstname;
    public String lastname;
    public String image;
    public String email;
    public String phone;
    public String class_name;
    public String remarque;

    public createStudent(String firstname, String lastname, String image, String email, String phone, String class_name, String remarque) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.image = image;
        this.email = email;
        this.phone = phone;
        this.class_name = class_name;
        this.remarque = remarque;
    }

    public createStudent(){

    }
}
