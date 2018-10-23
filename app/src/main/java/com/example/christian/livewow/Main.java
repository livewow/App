package com.company;

public class Main {

    public static void main(String[] args) {
	    User test1 = new User();
	    ContactInfo ciTest1 = new ContactInfo();

	    test1.setUsername("Tester1");
	    test1.setPassword("Test1234");
        ciTest1.setEmail("tester1@test.com");
        ciTest1.setPhoneNumber("(123)456-7890");
        test1.setContactInfo(ciTest1);

	    String username = "Tester2";
	    String password = "Test4321";
	    String email = "tester2@test.com";
	    String phoneNumber = "(908)765-4321";

        ContactInfo ciTest2 = new ContactInfo(phoneNumber, email);
	    User test2 = new User(username, password, ciTest2);

	    System.out.println("User: " + test1.getUsername());
	    System.out.println("Password :" + test1.getPassword());
	    System.out.println("Contact Info: ");
        System.out.println("     Email: " + test1.getContactInfo().getEmail());
        System.out.println("     Phone Number: " + test1.getContactInfo().getPhoneNumber());

	    System.out.println();
        System.out.println("User: " + test2.getUsername());
        System.out.println("Password: " + test2.getPassword());
        System.out.println("Contact Info: ");
        System.out.println("     Email: " + test2.getContactInfo().getEmail());
        System.out.println("     Phone Number: " + test2.getContactInfo().getPhoneNumber());
    }
}
