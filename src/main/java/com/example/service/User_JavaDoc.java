Here is the updated Java source code with detailed Javadoc comments added for the class, constructors, and public methods:

```java
package com.example.service;

/**
 * Represents a User object with properties id, name, email, and num.
 */
public class User {

    /**
     * The unique identifier of the user.
     */
    private Long id;

    /**
     * The name of the user.
     */
    private String name;

    /**
     * The email address of the user.
     */
    private String email;

    /**
     * The phone number of the user.
     */
    private String num;

    /**
     * Initializes a new User object with given id, name, email, and num.
     *
     * @param id      the unique identifier of the user
     * @param name    the name of the user
     * @param email   the email address of the user
     * @param num     the phone number of the user
     */
    public User(Long id, String name, String email, String num) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.num = num;
    }

    /**
     * Returns the unique identifier of the user.
     *
     * @return the unique identifier of the user
     */
    public Long getId() {
        return id;
    }

    /**
     * Returns the name of the user.
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the email address of the user.
     *
     * @return the email address of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email address of the user.
     *
     * @param email the new email address of the user
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
```