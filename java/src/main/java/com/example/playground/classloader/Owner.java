package com.example.playground.classloader;

public class Owner {
    private String name;
    private Gender gender;
    private int age;

    public Owner(String name) {
        this.name = name;
        this.gender = Gender.UNKNOWN;
        this.age = 0;
    }

    public Owner(String name, Gender gender, int age) {
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
