package com.example.example_btl_androidnc.students.model;

import java.util.Date;

public class Blog {
    private String id;
    private String title;
    private String description;
    private String content;
    private String image;
    private Date createdAt;

    public Blog(String id, String title, String description, String content, String image, Date createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
        this.image = image;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
