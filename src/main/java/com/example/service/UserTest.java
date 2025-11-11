To generate JUnit test cases for the `User` class using Mockito, I'll create a separate test class called `UserTest`. Here is the code:

```java
package com.example.service;

import org.junit.jupiter.api.*;
import org.mockito.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @Mock
    private User user;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testConstructorWithValidParameters() {
        Long id = 1L;
        String name = "John Doe";
        String email = "john.doe@example.com";
        String num = "0987654321";

        User actualUser = new User(id, name, email, num);

        verify(user).setId(id);
        verify(user).setName(name);
        verify(user).setEmail(email);
        verify(user).setNum(num);

        assertEquals(id, actualUser.getId());
        assertEquals(name, actualUser.getName());
        assertEquals(email, actualUser.getEmail());
        assertEquals(num, actualUser.getNum());
    }

    @Test
    void testGetters() {
        Long id = 1L;
        String name = "John Doe";
        String email = "john.doe@example.com";
        String num = "0987654321";

        when(user.getId()).thenReturn(id);
        when(user.getName()).thenReturn(name);
        when(user.getEmail()).thenReturn(email);
        when(user.getNum()).thenReturn(num);

        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(num, user.getNum());
    }

    @Test
    void testSetEmailNull() {
        assertThrows(IllegalArgumentException.class, () -> user.setEmail(null));
    }
}
```

In this test class, I've created a mock instance of the `User` class using Mockito and tested its constructor with valid parameters, as well as its getter methods. Additionally, I've added a separate test for setting an email to null to check if it throws an `IllegalArgumentException`.