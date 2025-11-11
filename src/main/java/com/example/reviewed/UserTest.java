Below is a sample JUnit test class for the `User` class using Mockito. The test cases include edge cases, null checks, and exception scenarios.

```java
package com.example.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserTest {

    @Mock
    private User user;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testUserConstruction() {
        Long id = 1L;
        String name = "John Doe";
        String email = "john.doe@example.com";
        String lName = "Doe";

        User actualUser = new User(id, name, email, lName);

        assertEquals(id, actualUser.getId());
        assertEquals(name, actualUser.getName());
        assertEquals(email, actualUser.getEmail());
        assertEquals(lName, actualUser.getLName());
    }

    @Test(expected = NullPointerException.class)
    public void testUserConstructionWithNullId() {
        String name = "John Doe";
        String email = "john.doe@example.com";
        String lName = "Doe";

        new User(null, name, email, lName);
    }

    @Test(expected = NullPointerException.class)
    public void testUserConstructionWithNullName() {
        Long id = 1L;
        String email = "john.doe@example.com";
        String lName = "Doe";

        new User(id, null, email, lName);
    }

    @Test(expected = NullPointerException.class)
    public void testUserConstructionWithNullEmail() {
        Long id = 1L;
        String name = "John Doe";
        String lName = "Doe";

        new User(id, name, null, lName);
    }

    @Test(expected = NullPointerException.class)
    public void testUserConstructionWithNullLName() {
        Long id = 1L;
        String name = "John Doe";
        String email = "john.doe@example.com";

        new User(id, name, email, null);
    }

    @Test
    public void testSetEmail() {
        String originalEmail = "john.doe@example.com";
        user.setEmail(originalEmail);

        assertEquals(originalEmail, user.getEmail());
    }

    @Test(expected = NullPointerException.class)
    public void testSetEmailWithNullEmail() {
        String originalEmail = "john.doe@example.com";
        user.setEmail(null);
    }

    @Test
    public void testSetLame() {
        String originalLName = "Doe";
        user.setLame(originalLName);

        assertEquals(originalLName, user.getLName());
    }

    @Test(expected = NullPointerException.class)
    public void testSetLameWithNullLName() {
        String originalLName = "Doe";
        user.setLame(null);
    }
}
```

This test class covers construction of the `User` object with various edge cases (null inputs), as well as testing setting of email and lName properties. It also includes a test for exception scenarios (NullPointerException) when input parameters are null during construction and property setting.