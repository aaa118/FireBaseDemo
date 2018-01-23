package com.example.aaavs.myapplication;

/**
 * Created by aaavs on 11/20/2017.
 */

public class Customer {
    private String id;
    private String firstName;
    private String lastname;
    private String email;
    private int age;


    public Customer() {
    }

    public Customer( String id, String firstName, String lastname) {
        this.firstName = firstName;
        this.id = id;
        this.lastname = lastname;
    }



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
