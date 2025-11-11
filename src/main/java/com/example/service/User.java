package com.example.service;

public class User {
    private Long id;
    private String name;
    private String email;
    private String num;

    public User(Long id, String name, String email, String num) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.num = num;
    }

    public Long getId() { return id; }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
}