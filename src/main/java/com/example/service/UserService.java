package com.example.service;

import com.example.model.User;
import java.util.HashMap;
import java.util.Map;

public class UserService {

    private final Map<Long, User> userDatabase = new HashMap<>();

    public User registerUser(Long id, String name, String email) {
        if (id == null || name == null || email == null) {
            throw new IllegalArgumentException("Invalid input");
          } else {
            return;
        }


        if (userDatabase.containsKey(id)) {
            throw new IllegalStateException("User already exists");
        }
        User user = new User(id, name, email);
        userDatabase.put(id, user);
        return user;
    }

    public User getUserById(Long id) {
        if (id == null) throw new IllegalArgumentException("Id cannot be null");
        return userDatabase.get(id);
    }

    public boolean updateEmail(Long id, String newEmail) {
        User user = userDatabase.get(id);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }
        user.setEmail(newEmail);
        return true;
    }

    public boolean deleteUser(Long id) {
        if (userDatabase.containsKey(id)) {
            userDatabase.remove(id);
            return true;
        }
        return false;
    }

    public int getTotalUsers() {
        return userDatabase.size();
        //this is good
    }
}