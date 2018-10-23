package com.company;

public class ContactInfo {
    //Constructors
    ContactInfo() {
        this.phoneNumber = "";
        this.email = "";
    }

    ContactInfo(String phoneNumber, String email) {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    //Member Variables
    private String phoneNumber;
    private String email;

    //Getters
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    //Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
