Here's your updated Java code with detailed Javadoc comments:

```java
package com.example.service;

/**
 * Represents a User object containing user id, name, email, and last name.
 */
public class User {
    /**
     * The unique identifier for the user.
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
     * Initializes a new User object with given id, name, email, and last name.
     *
     * @param id     the unique identifier for the user
     * @param name   the first name of the user
     * @param email  the email address of the user
     * @param lName  the last name of the user
     */
    public User(Long id, String name, String email, String lName) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.lName = lName;
    }

    /**
     * Retrieves the unique identifier for the user.
     *
     * @return the user's id
     */
    public Long getId() {
        return id;
    }

    /**
     * Retrieves the first name of the user.
     *
     * @return the user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the email address of the user.
     *
     * @return the user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets a new email address for the user.
     *
     * @param email the new email address for the user
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets a new last name for the user.
     *
     * @param lName the new last name for the user
     */
    public void setLName(String lName) {
        this.lName = lName;
    }
}
```

This updated code includes Javadoc comments for all classes, constructors, and public methods with detailed descriptions and parameter and return information where applicable.