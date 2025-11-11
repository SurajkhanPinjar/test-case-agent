Here's a simple JUnit test class for the `User` class using Mockito. I've included edge cases, null checks, and exception scenarios.

```java
package com.example.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserTest {

    @Mock
    private User user;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testUserCreationWithValidValues() {
        Long id = 1L;
        String name = "John Doe";
        String email = "john.doe@example.com";
        String lName = "Doe";

        User createdUser = new User(id, name, email, lName);

        assertAll(() -> assertEquals(id, createdUser.getId()),
                   () -> assertEquals(name, createdUser.getName()),
                   () -> assertEquals(email, createdUser.getEmail()),
                   () -> assertEquals(lName, createdUser.getLName()));
    }

    @Test
    void testSetEmailWithNull() {
        String nullEmail = null;
        userService.setEmail(user, nullEmail);

        verify(user).setEmail(null);
        assertThrows(IllegalArgumentException.class, () -> userService.setEmail(user, nullEmail));
    }

    @Test
    void testSetLNameWithNull() {
        String nullLName = null;
        userService.setLName(user, nullLName);

        verify(user).setLName(null);
        assertThrows(IllegalArgumentException.class, () -> userService.setLName(user, nullLName));
    }

    @Test
    void testGetters() {
        Long id = 1L;
        String name = "John Doe";
        String email = "john.doe@example.com";
        String lName = "Doe";

        User userUnderTest = new User(id, name, email, lName);

        assertEquals(id, userUnderTest.getId());
        assertEquals(name, userUnderTest.getName());
        assertEquals(email, userUnderTest.getEmail());
        assertEquals(lName, userUnderTest.getLName());
    }
}
```

This test class checks the creation of a `User` object with valid values and tests the edge cases where the email and last name are null, as well as verifying the getters for each field. Additionally, it includes an exception scenario when trying to set the email or last name with a null value.