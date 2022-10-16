package com.example.PathFinder.viewModels.user;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


public class UserRegistrationInputModel {
    @NotBlank
    @Length(min = 5, max = 20)
    private String username;

    @NotBlank
    @Length(min = 5, max = 20)
    private String fullName;

    @NotBlank
    @Email
    private String email;

    @Min(0)
    @Max(90)
    private int age;

    @NotBlank
    @Length(min = 5, max = 20)
    private String password;

    @NotBlank
    @Length(min = 5, max = 20)
    private String confirmPassword;

    public String getUsername() {
        return username;
    }

    public UserRegistrationInputModel setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public UserRegistrationInputModel setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserRegistrationInputModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getAge() {
        return age;
    }

    public UserRegistrationInputModel setAge(int age) {
        this.age = age;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegistrationInputModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegistrationInputModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
