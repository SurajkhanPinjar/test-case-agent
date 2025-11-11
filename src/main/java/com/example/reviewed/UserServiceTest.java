Here's a JUnit test class using Mockito for the given UserService class. This test case includes edge cases, null checks, and exception scenarios:

```java
package com.example.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.model.User;

public class UserServiceTest {

    @Mock
    private Map<Long, User> userDatabase;

    private UserService userService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userDatabase);
    }

    @Test
    public void testRegisterUser_ValidInput_ReturnsUser() {
        Long id = 1L;
        String name = "John Doe";
        String email = "john.doe@example.com";
        User user = new User(id, name, email);

        when(userDatabase.put(id, user)).thenReturn(null);

        assertDoesNotThrow(() -> userService.registerUser(id, name, email));
        verify(userDatabase).put(id, user);
    }

    @Test
    public void testRegisterUser_NullInput_ThrowsIllegalArgumentException() {
        Long id = null;
        String name = "John Doe";
        String email = "john.doe@example.com";

        assertThrows(IllegalArgumentException.class, () -> userService.registerUser(id, name, email));
    }

    @Test
    public void testRegisterUser_ExistingId_ThrowsIllegalStateException() {
        Long id = 1L;
        String name = "John Doe";
        String email = "john.doe@example.com";
        User existingUser = new User(id, name, email);
        when(userDatabase.put(id, existingUser)).thenReturn(existingUser);

        assertThrows(IllegalStateException.class, () -> userService.registerUser(id, name, email));
    }

    @Test
    public void testGetUserById_ValidId_ReturnsUser() {
        Long id = 1L;
        User user = new User(id, "John Doe", "john.doe@example.com");
        when(userDatabase.get(id)).thenReturn(user);

        assertEquals(user, userService.getUserById(id));
    }

    @Test
    public void testGetUserById_NullId_ThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> userService.getUserById(null));
    }

    @Test
    public void testUpdateEmail_ValidUserIdAndEmail_ReturnsTrue() {
        Long id = 1L;
        String newEmail = "john.doe@example.com";
        User user = new User(id, "John Doe", "oldemail@example.com");
        when(userDatabase.get(id)).thenReturn(user);

        assertTrue(userService.updateEmail(id, newEmail));
        verify(userDatabase).get(id);
    }

    @Test
    public void testUpdateEmail_NullUserId_ThrowsIllegalStateException() {
        Long id = null;
        String newEmail = "john.doe@example.com";

        assertThrows(IllegalStateException.class, () -> userService.updateEmail(id, newEmail));
    }

    @Test
    public void testUpdateEmail_NonExistentUserId_ThrowsIllegalStateException() {
        Long id = 1L;
        String newEmail = "john.doe@example.com";
        when(userDatabase.get(id)).thenReturn(null);

        assertThrows(IllegalStateException.class, () -> userService.updateEmail(id, newEmail));
    }

    @Test
    public void testDeleteUser_ValidUserId_ReturnsTrue() {
        Long id = 1L;
        User user = new User(id, "John Doe", "john.doe@example.com");
        when(userDatabase.containsKey(id)).thenReturn(true);
        when(userDatabase.remove(id)).thenReturn(user);

        assertTrue(userService.deleteUser(id));
        verify(userDatabase).containsKey(id);
        verify(userDatabase).remove(id);
    }

    @Test
    public void testDeleteUser_NonExistentUserId_ReturnsFalse() {
        Long id = 1L;
        when(userDatabase.containsKey(id)).thenReturn(false);

        assertFalse(userService.deleteUser(id));
        verify(userDatabase).containsKey(id);
    }

    @Test
    public void testGetTotalUsers_EmptyUserDatabase_ReturnsZero() {
        assertEquals(0, userService.getTotalUsers());
        verifyNoInteractions(userDatabase);
    }

    @Test
    public void testGetTotalUsers_NonEmptyUserDatabase_ReturnsCorrectSize() {
        Long id1 = 1L;
        User user1 = new User(id1, "John Doe", "john.doe@example.com");
        Long id2 = 2L;
        User user2 = new User(id2, "Jane Doe", "jane.doe@example.com");
        when(userDatabase.size()).thenReturn(2);
        when(userDatabase.get(id1)).thenReturn(user1);
        when(userDatabase.get(id2)).thenReturn(user2);

        assertEquals(2, userService.getTotalUsers());
        verify(userDatabase).size();
    }
}
```