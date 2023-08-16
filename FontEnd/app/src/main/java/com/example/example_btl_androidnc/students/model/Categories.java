package com.example.example_btl_androidnc.students.model;

import java.util.Date;

public class Categories {
    private String id;
    private Date created_at;
    private Date modified_at;
    private String slug;

    public Categories(String id, Date created_at, Date modified_at, String slug) {
        this.id = id;
        this.created_at = created_at;
        this.modified_at = modified_at;
        this.slug = slug;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getModified_at() {
        return modified_at;
    }

    public void setModified_at(Date modified_at) {
        this.modified_at = modified_at;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "id='" + id + '\'' +
                ", created_at=" + created_at +
                ", modified_at=" + modified_at +
                ", slug='" + slug + '\'' +
                '}';
    }
}
