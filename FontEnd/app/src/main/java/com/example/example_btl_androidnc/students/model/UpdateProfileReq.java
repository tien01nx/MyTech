package com.example.example_btl_androidnc.students.model;

import java.util.Date;

public class UpdateProfileReq {
    private String name;

    private String gender ;
    private String phone;

    private String address;
    private String dateOfBirth;

    private String image ;

    public UpdateProfileReq(String name, String gender, String phone, String address, String dateOfBirth, String image) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
        this.image = image;
    }

    public UpdateProfileReq() {
    }

    @Override
    public String toString() {
        return "UpdateProfileReq{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", image='" + image + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
