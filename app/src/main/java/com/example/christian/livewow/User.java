package com.company;

public class User {
    //Constructors
    User() {
        this.username = "";
        this.password = "";
    }

    User(String username, String password, ContactInfo contactInfo) {
        this.username = username;
        this.password = password;
        this.contactInfo = contactInfo;
    }

    //Member Variables
    private String username;
    private String password;
    private ContactInfo contactInfo;

    //Getters
    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    //Setters
    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }
}
