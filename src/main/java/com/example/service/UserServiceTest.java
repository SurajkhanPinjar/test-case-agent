Here's a simple JUnit test class using Mockito for the given `UserService` class. I've included edge cases, null checks, and exception scenarios:

```java
package com.example.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.model.User;

class UserServiceTest {

    @Mock
    private User mockUser;

    private UserService userService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService();
    }

    @Test
    void registerUser_ValidInput_ReturnsUser() {
        Long id = 1L;
        String name = "John Doe";
        String email = "john.doe@example.com";

        when(mockUser.setEmail(anyString())).thenReturn(mockUser);

        User user = userService.registerUser(id, name, email);

        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        verify(userDatabase, times(1)).put(id, mockUser);
    }

    @Test
    void registerUser_NullInput_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(null, "name", "email"));
        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(1L, null, "email"));
        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(1L, "name", null));
    }

    @Test
    void registerUser_UserAlreadyExists_ThrowsException() {
        Long id = 1L;
        String name = "John Doe";
        String email = "john.doe@example.com";

        userDatabase.put(id, mockUser);

        assertThrows(IllegalStateException.class, () -> userService.registerUser(id, name, email));
    }

    @Test
    void getUserById_ValidId_ReturnsUser() {
        Long id = 1L;

        when(userDatabase.get(id)).thenReturn(mockUser);

        User user = userService.getUserById(id);

        assertEquals(id, user.getId());
    }

    @Test
    void getUserById_NullId_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> userService.getUserById(null));
    }

    @Test
    void updateEmail_ValidInput_ReturnsTrue() {
        Long id = 1L;
        String newEmail = "new.email@example.com";

        when(userDatabase.get(id)).thenReturn(mockUser);

        assertTrue(userService.updateEmail(id, newEmail));
        assertEquals(newEmail, mockUser.getEmail());
    }

    @Test
    void updateEmail_NullIdOrNonExistentUser_ThrowsException() {
        assertThrows(IllegalStateException.class, () -> userService.updateEmail(null, "email"));
        userDatabase.remove(1L);
        assertThrows(IllegalStateException.class, () -> userService.updateEmail(1L, "new.email@example.com"));
    }

    @Test
    void deleteUser_ValidInput_ReturnsTrue() {
        Long id = 1L;

        assertTrue(userService.deleteUser(id));
        assertFalse(userDatabase.containsKey(id));
    }

    @Test
    void deleteUser_NullIdOrNonExistentUser_ReturnsFalse() {
        assertFalse(userService.deleteUser(null));
        userDatabase.remove(1L);
        assertFalse(userService.deleteUser(1L));
    }

    @Test
    void getTotalUsers_NoUsers_ReturnsZero() {
        assertEquals(0, userService.getTotalUsers());
    }

    @Test
    void getTotalUsers_MultipleUsers_ReturnsCorrectCount() {
        Long id1 = 1L;
        Long id2 = 2L;
        when(userDatabase.size()).thenReturn(2);

        userService.registerUser(id1, "name1", "email1");
        userService.registerUser(id2, "name2", "email2");

        assertEquals(2, userService.getTotalUsers());
    }

    private Map<Long, User> userDatabase = new HashMap<>();
}
```