package com.example.sql_lab;

/**
 * Created by smann
 */
public class Student {
    int _id;
    String _name;
    String _phoneNumber;

    public Student() {}

    public Student(int id, String name, String phoneNum) {
        this._id = id; this._name = name;
        this._phoneNumber = phoneNum;

    }
    public Student(String name, String phoneNumber) {
        this._name = name; this._phoneNumber =
                phoneNumber;
    }
    public int getID() {
        return this._id;
    }
    public void setID(int id) {
        this._id = id;
    }
    public String getName() {
        return this._name;
    }
    public void setName(String name) {
        this._name = name;
    }
    public String getPhone() {
        return this._phoneNumber;
    }
    public void setPhone(String phone) {
        this._phoneNumber = phone;
    }



}