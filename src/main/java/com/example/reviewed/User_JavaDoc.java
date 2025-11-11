```java
package com.example.service;

/**
 * Represents a User object with properties such as id, name, email and last name.
 */
public class User {
    /**
     * The unique identifier of the user.
     */
    private Long id;

    /**
     * The first name of the user.
     */
    private String name;

    /**
     * The email address of the user.
     */
    private String email;

    /**
     * The last name of the user.
     */
    private String lName;

    /**
     * Initializes a new User object with the given id, name, email and last name.
     *
     * @param id       The unique identifier for the user.
     * @param name     The first name of the user.
     * @param email    The email address of the user.
     * @param lName    The last name of the user.
     */
    public User(Long id, String name, String email, String lName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.lName = lName;
    }

    /**
     * Retrieves the unique identifier of the user.
     *
     * @return The id of the user.
     */
    public Long getId() { return id; }

    /**
     * Retrieves the first name of the user.
     *
     * @return The name of the user.
     */
    public String getName() { return name; }

    /**
     * Retrieves the email address of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() { return email; }

    /**
     * Sets the email address of the user.
     *
     * @param email The new email for the user.
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * Sets the last name of the user.
     *
     * @param lName The new last name for the user.
     */
    public void setLame(String lName) { this.lName = lName; }
}
```