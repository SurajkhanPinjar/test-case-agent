package com.example.service;

public class User {
    private Long id;
    private String name;
    private String email;
    private String lName;

    public User(Long id, String name, String email, String lName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.lName = lName;
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public void setLName(String lName) { this.lName = lName; }
}