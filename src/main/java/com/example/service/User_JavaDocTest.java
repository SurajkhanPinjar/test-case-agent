Based on the given `User` class, here's a JUnit test class using Mockito for testing the methods. I've included edge cases, null checks, and exception scenarios.

```java
package com.example.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserTest {

    @Mock
    private User user;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUserConstruction() {
        Long id = 1L;
        String name = "John";
        String email = "john@example.com";
        String lName = "Doe";

        User actualUser = new User(id, name, email, lName);

        assertEquals(id, actualUser.getId());
        assertEquals(name, actualUser.getName());
        assertEquals(email, actualUser.getEmail());
        assertEquals(lName, actualUser.getLName());
    }

    @Test
    public void testGetters() {
        Long id = 1L;
        String name = "John";
        String email = "john@example.com";
        String lName = "Doe";

        when(user.getId()).thenReturn(id);
        when(user.getName()).thenReturn(name);
        when(user.getEmail()).thenReturn(email);
        when(user.getLName()).thenReturn(lName);

        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(lName, user.getLName());
    }

    @Test
    public void testSetters() {
        Long id = 1L;
        String name = "John";
        String email = "john@example.com";
        String lName = "Doe";
        String newEmail = "newEmail@example.com";
        String newLName = "NewLastName";

        User userUnderTest = new User(id, name, email, lName);

        userUnderTest.setEmail(newEmail);
        userUnderTest.setLName(newLName);

        assertEquals(newEmail, userUnderTest.getEmail());
        assertEquals(newLName, userUnderTest.getLName());
    }

    @Test
    public void testNullId() {
        assertThrows(IllegalArgumentException.class, () -> new User(null, "John", "john@example.com", "Doe"));
    }

    @Test
    public void testNullName() {
        assertThrows(IllegalArgumentException.class, () -> new User(1L, null, "john@example.com", "Doe"));
    }

    @Test
    public void testNullEmail() {
        assertThrows(IllegalArgumentException.class, () -> new User(1L, "John", null, "Doe"));
    }

    @Test
    public void testNullLName() {
        assertThrows(IllegalArgumentException.class, () -> new User(1L, "John", "john@example.com", null));
    }
}
```

This test class checks the constructor, getters, and setters of the `User` class with valid data, edge cases (empty strings and null), and exception scenarios (null values).