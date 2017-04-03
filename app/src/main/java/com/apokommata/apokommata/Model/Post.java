package com.apokommata.apokommata.Model;

/**
 * Created by dito on 03/04/17.
 */

public class Post {
    private String name;
    private String pic;
    private String description;
    private int category;
    private int method;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public Post(String name, String pic, String description, int category, int method) {
        this.name = name;
        this.pic = pic;
        this.description = description;
        this.category = category;
        this.method = method;
    }
}
