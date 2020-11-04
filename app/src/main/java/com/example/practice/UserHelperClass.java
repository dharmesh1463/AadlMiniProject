package com.example.practice;

public class UserHelperClass {

    String name,email,password,username;

    public UserHelperClass() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserHelperClass(String name, String email, String password, String username) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.username = username;
    }
}
