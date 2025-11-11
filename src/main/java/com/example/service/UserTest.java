Here's a basic JUnit test class for the `User` class using Mockito. I've included some unit tests that cover edge cases, null checks, and exception scenarios:

```java
package com.example.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class UserTest {

    @Mock
    private UserService userService;

    private User user;

    @Before
    public void setup() {
        user = new User(1L, "John Doe", "john.doe@example.com");
    }

    @Test
    public void testConstructorWithValidInputs() {
        // No need to mock anything for this test case
        assertNotNull(user);
        assertEquals(1L, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullId() {
        User user = new User(null, "John Doe", "john.doe@example.com");
        // No need to mock anything for this test case
        assertNull(user);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullName() {
        User user = new User(1L, null, "john.doe@example.com");
        // No need to mock anything for this test case
        assertNull(user);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullEmail() {
        User user = new User(1L, "John Doe", null);
        // No need to mock anything for this test case
        assertNull(user);
    }

    @Test
    public void testGetters() {
        // No need to mock anything for this test case
        assertEquals(1L, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john.doe@example.com", user.getEmail());
    }

    @Test
    public void testSetters() {
        // No need to mock anything for this test case
        user.setEmail("john.updated@example.com");
        assertEquals("john.updated@example.com", user.getEmail());
    }

    @Test(expected = NullPointerException.class)
    public void testSetEmailWithNullEmail() {
        user.setEmail(null);
        // No need to mock anything for this test case
    }

    @Test
    public void testSaveUserSuccess() {
        when(userService.saveUser(user)).thenReturn(user);
        User savedUser = userService.saveUser(user);
        verify(userService).saveUser(user);
        assertEquals(user, savedUser);
    }

    @Test(expected = RuntimeException.class)
    public void testSaveUserFailure() {
        when(userService.saveUser(user)).thenThrow(new RuntimeException("Save user failed"));
        userService.saveUser(user);
        verify(userService).saveUser(user);
    }
}
```

This test class includes tests for the constructor with valid and null inputs, getters and setters, and saving a user using a mock `UserService`. The test for saving a user includes both success and failure scenarios.