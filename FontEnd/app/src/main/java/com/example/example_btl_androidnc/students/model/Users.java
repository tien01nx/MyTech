package com.example.example_btl_androidnc.students.model;

import java.io.Serializable;
import java.util.List;

public class Users implements Serializable {
    private String id;
    private String address;
    private String avatar;
    private String email;
    private String gender;
    private String name;

    private String password;
    private String phone;
    private String status;

    private String token;
    private String refreshToken;
    private String type;
    private List<String> roles;



    private String dateOfBirth;
    private String image;


    private boolean attendance;

    public boolean isChecked() {
        return attendance;
    }

    public void setChecked(boolean checked) {
        this.attendance = checked;
    }

    public Users(String id, boolean attendance) {
        this.id = id;
        this.attendance = attendance;
    }

    private List<UserCourse> UserCourse;

    public Users() {
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Users(String id, String address, String avatar, String email, String gender, String name, String password, String phone, String status) {
        this.id = id;
        this.address = address;
        this.avatar = avatar;
        this.email = email;
        this.gender = gender;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.status = status;
        this.token = token;
        this.refreshToken = refreshToken;
        this.type = type;
        this.roles = roles;
    }

    public Users(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Users(String name, String email , String password) {
        this.email = email;
        this.name = name;
        this.password = password;
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




    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id='" + id + '\'' +
                ", address='" + address + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", status='" + status + '\'' +
                ", token='" + token + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", type='" + type + '\'' +
                ", roles=" + roles +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", image='" + image + '\'' +
                ", UserCourse=" + UserCourse +
                '}';
    }
}
