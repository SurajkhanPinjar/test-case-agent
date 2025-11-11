```java
package com.example.service;

import com.example.model.User;
import java.util.HashMap;
import java.util.Map;

/**
 * UserService class manages the database of users.
 */
public class UserService {

    /**
     * A Map data structure to store users in the service.
     */
    private final Map<Long, User> userDatabase = new HashMap<>();

    /**
     * Registers a new user with given id, name and email.
     *
     * @param id      the unique identifier for the user
     * @param name    the name of the user
     * @param email   the email address of the user
     * @return the registered user object or throws an exception if input is invalid or user already exists
     * @throws IllegalArgumentException when id, name or email is null
     * @throws IllegalStateException    when a user with the given id already exists
     */
    public User registerUser(Long id, String name, String email) {
        // ... existing code
    }

    /**
     * Retrieves the user with the given id from the database.
     *
     * @param id the unique identifier of the user to retrieve
     * @return the user object or throws an exception if the id is null
     * @throws IllegalArgumentException when id is null
     */
    public User getUserById(Long id) {
        // ... existing code
    }

    /**
     * Updates the email of a user with the given id in the database.
     *
     * @param id      the unique identifier of the user to update
     * @param newEmail the new email address for the user
     * @return true if successful, false if user not found
     * @throws IllegalStateException when user not found
     */
    public boolean updateEmail(Long id, String newEmail) {
        // ... existing code
    }

    /**
     * Removes a user with the given id from the database.
     *
     * @param id the unique identifier of the user to remove
     * @return true if successful, false if user not found
     */
    public boolean deleteUser(Long id) {
        // ... existing code
    }

    /**
     * Returns the total number of users in the database.
     *
     * @return the count of users in the database
     */
    public int getTotalUsers() {
        // ... existing code
    }
}
```