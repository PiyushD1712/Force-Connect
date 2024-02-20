package com.example.forceconnect.instructor.models;

public class InstructorUser {
    private String name;
    private String position;
    private String yearOfExperience;
    private String location;
    private String imgUrl;

    public InstructorUser() {
    }

    public InstructorUser(String name, String position, String yearOfExperience, String location,String imgUrl) {
        this.name = name;
        this.position = position;
        this.yearOfExperience = yearOfExperience;
        this.location = location;
        this.imgUrl = imgUrl;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getYearOfExperience() {
        return yearOfExperience;
    }

    public void setYearOfExperience(String yearOfExperience) {
        this.yearOfExperience = yearOfExperience;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
