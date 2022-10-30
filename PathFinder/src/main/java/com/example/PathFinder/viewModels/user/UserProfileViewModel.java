package com.example.PathFinder.viewModels.user;

public class UserProfileViewModel {

    private String username;

    private String email;

    private String fullName;

    private int age;

    private String level;

    public UserProfileViewModel() {}

    public UserProfileViewModel(String username, String email, String fullName, int age, String level) {
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.age = age;
        this.level = level;
    }

    public String getUsername() {
        return username;
    }

    public UserProfileViewModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserProfileViewModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserProfileViewModel setAge(int age) {
        this.age = age;
        return this;
    }

    public String getLevel() {
        return level;
    }

    public UserProfileViewModel setLevel(String level) {
        this.level = level;
        return this;
    }
}
