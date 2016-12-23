package com.example.android.networkjsondemo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Chaker on 12/22/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private  String name;
    private int age;
    private float grade;
    private  String password;

    public User(){}

     public User(String name,int age, float grade, String password) {
         this.name = name;
         this.age = age;
        this.grade = grade;
        this.password = password;

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


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("User name: " + getName() +"\n");
        sb.append("Age: " + getAge() + ", ");
        sb.append("Age: " + getGrade() + ", ");
        sb.append("Password: " + getPassword() +"\n");


        return sb.toString();
    }


}
