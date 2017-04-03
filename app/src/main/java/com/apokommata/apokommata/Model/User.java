package com.apokommata.apokommata.Model;

/**
 * Created by dito on 28/03/17.
 */

public class User {
    private String id;
    private String email;
    private String fullName;
    private String pic;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public User(String id, String email, String fullName, String pic,String location, String phone) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.pic = pic;
        this.location = location;
        this.phone = phone;
    }
}
