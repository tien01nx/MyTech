package com.example.example_btl_androidnc.students.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Course implements Serializable {

    private String id;
    private String name;
    private String description;
    private String status;

    private String price;

    private String level;

    private String image;

    private  String publishedAt;
    private String expiredAt;
    @SerializedName("users")
    //private Teacher teacher;
    private Users users ;

    private List<String> teacheNames;



    public Course(String id, String name, String description, String status, String price, String level, String image, String publishedAt, String expiredAt, Users users) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.price = price;
        this.level = level;
        this.image = image;
        this.publishedAt = publishedAt;
        this.expiredAt = expiredAt;
        this.users = users;
    }

    public List<String> getTeacheNames() {
        return teacheNames;
    }

    public void setTeacheNames(List<String> teacheNames) {
        this.teacheNames = teacheNames;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(String expiredAt) {
        this.expiredAt = expiredAt;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", price='" + price + '\'' +
                ", level='" + level + '\'' +
                ", image='" + image + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", expiredAt='" + expiredAt + '\'' +
                ", users=" + users +
                ", teacheNames=" + teacheNames +
                '}';
    }
}
