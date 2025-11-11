```java
package com.example.service;

import com.example.model.User;
import java.util.HashMap;
import java.util.Map;

/**
 * UserService class manages the database of users. It provides methods for registering, retrieving, updating, and deleting users.
 */
public class UserService {

    /**
     * Private map to store the database of users.
     */
    private final Map<Long, User> userDatabase = new HashMap<>();

    /**
     * Registers a new user with the given id, name, and email.
     *
     * @param id          the unique identifier for the user
     * @param name        the name of the user
     * @param email       the email address of the user
     * @return the newly registered user object or null if an error occurs
     * @throws IllegalArgumentException when the input is invalid (id, name, or email are null)
     * @throws IllegalStateException    when a user with the same id already exists in the database
     */
    public User registerUser(Long id, String name, String email) {
        if (id == null || name == null || email == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        if (userDatabase.containsKey(id)) {
            throw new IllegalStateException("User already exists");
        }

        User user = new User(id, name, email);
        userDatabase.put(id, user);
        return user;
    }

    /**
     * Retrieves the user with the given id from the database.
     *
     * @param id the unique identifier of the user to retrieve
     * @return the user object or null if not found in the database
     * @throws IllegalArgumentException when the input is invalid (id is null)
     */
    public User getUserById(Long id) {
        if (id == null) throw new IllegalArgumentException("Id cannot be null");
        return userDatabase.get(id);
    }

    /**
     * Updates the email of the user with the given id in the database.
     *
     * @param id          the unique identifier of the user to update
     * @param newEmail    the new email address for the user
     * @return true if the user was found and updated, false otherwise
     * @throws IllegalStateException when the user is not found in the database
     */
    public boolean updateEmail(Long id, String newEmail) {
        User user = userDatabase.get(id);
        if (user == null) {
            throw new IllegalStateException("User not found");
        }
        user.setEmail(newEmail);
        return true;
    }

    /**
     * Deletes the user with the given id from the database.
     *
     * @param id the unique identifier of the user to delete
     * @return true if the user was found and deleted, false otherwise
     */
    public boolean deleteUser(Long id) {
        if (userDatabase.containsKey(id)) {
            userDatabase.remove(id);
            return true;
        }
        return false;
    }

    /**
     * Returns the total number of users in the database.
     *
     * @return the count of users in the database
     */
    public int getTotalUsers() {
        return userDatabase.size();
    }
}
```