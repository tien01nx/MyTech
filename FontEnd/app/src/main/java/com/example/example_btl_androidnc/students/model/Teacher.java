package com.example.example_btl_androidnc.students.model;

import java.io.Serializable;
import java.util.List;

public class Teacher implements Serializable {
    private String id;
    private String address;
    private String avatar;
    private String email;
    private String gender;
    private String name;
    private String password;
    private String phone;
    private String status;
    private List<String> roles;


    public Teacher(String id, String name, String email, String gender, String password, String address, String phone, String avatar, String status, List<String> roles) {
        this.id = id;
        this.address = address;
        this.avatar = avatar;
        this.email = email;
        this.gender = gender;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.status = status;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Teacher{" + "id='" + id + '\'' + ", address='" + address + '\'' + ", avatar='" + avatar + '\'' + ", email='" + email + '\'' + ", gender='" + gender + '\'' + ", name='" + name + '\'' + ", password='" + password + '\'' + ", phone='" + phone + '\'' + ", roles='" + roles + '\'' + '}';
    }
}
