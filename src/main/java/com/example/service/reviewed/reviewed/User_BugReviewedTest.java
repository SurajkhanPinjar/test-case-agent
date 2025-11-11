Based on your optimized User class, here is an example of a JUnit test class using Mockito to test the User class. I've included edge cases, null checks, and exception scenarios for the constructor, getters, and setter methods.

```java
package com.example.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserTest {
    private User user;

    @BeforeEach
    public void setup() {
        user = new User(1L, "John Doe", "john.doe@example.com");
    }

    @Test
    public void testConstructor_ValidArguments() {
        Assertions.assertAll(
                () -> assertEquals(1L, user.getId()),
                () -> assertEquals("John Doe", user.getName()),
                () -> assertEquals("john.doe@example.com", user.getEmail())
        );
    }

    @Test
    public void testConstructor_NullId() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new User(null, "John Doe", "john.doe@example.com"));
    }

    @Test
    public void testConstructor_NullName() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new User(1L, null, "john.doe@example.com"));
    }

    @Test
    public void testConstructor_NullEmail() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new User(1L, "John Doe", null));
    }

    @Test
    public void testGetters() {
        Mockito.when(user.getId()).thenReturn(2L);
        Mockito.when(user.getName()).thenReturn("Jane Doe");
        Mockito.when(user.getEmail()).thenReturn("jane.doe@example.com");

        Assertions.assertAll(
                () -> assertEquals(2L, user.getId()),
                () -> assertEquals("Jane Doe", user.getName()),
                () -> assertEquals("jane.doe@example.com", user.getEmail())
        );
    }

    @Test
    public void testSetter_ValidEmail() {
        user.setEmail("new.email@example.com");
        Mockito.verify(user).validateEmail("new.email@example.com");
        Assertions.assertEquals("new.email@example.com", user.getEmail());
    }

    @Test
    public void testSetter_InvalidEmail() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> user.setEmail("invalid"));
    }
}
```
This test class covers constructing a User object with valid and null arguments, testing the getters using Mockito, and testing the setter method with both valid and invalid emails.