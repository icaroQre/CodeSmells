package EHMS.util;

import java.util.Date;
import java.util.Scanner;

public class Person {
    protected String firstName;
    protected String lastName;
    protected String emailAddress;
    protected String gender;
    protected int age;
    protected Date dob;
    protected String contactNumber;
    protected String city;
    protected String state;
    protected String country;
    protected String address;
    protected Date registrationDate;
    Scanner sc = new Scanner(System.in);

    // Getters e Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    // Coleta informações do usuário
    protected void collectUserInformation() {
        System.out.println("First Name:");
        this.firstName = sc.next();
        System.out.println("Last Name:");
        this.lastName = sc.next();
        System.out.println("Email Address:");
        this.emailAddress = sc.next();
        System.out.println("Gender:");
        this.gender = sc.next();
        System.out.println("Age:");
        this.age = sc.nextInt();
        System.out.println("Contact Number:");
        this.contactNumber = sc.next();
        System.out.println("City:");
        this.city = sc.next();
        System.out.println("State:");
        this.state = sc.next();
        System.out.println("Country:");
        this.country = sc.next();
        System.out.println("Address:");
        this.address = sc.next();
    }
}
