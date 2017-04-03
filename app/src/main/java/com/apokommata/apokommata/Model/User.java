package com.apokommata.apokommata.Model;

/**
 * Created by dito on 28/03/17.
 */

public class User {
    private String id;
    private String email;
    private String fullName;
    private String location;
    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return fullName;
    }

    public void setFirstName(String fullName) {
        this.fullName = fullName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User(String id, String email, String fullName, String location, String phone) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.location = location;
        this.phone = phone;
    }
}
