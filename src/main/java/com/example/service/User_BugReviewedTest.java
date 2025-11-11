Based on the optimized and fixed version you've provided, here are the JUnit test cases using Mockito for the `User` class. This includes edge cases, null checks, and exception scenarios.

```java
package com.example.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ValidationException;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    @InjectMocks
    private User user;

    @Mock
    private Long id;
    @Mock
    private String name;
    @Mock
    private String email;
    @Mock
    private String lName;

    @Before
    public void setup() {
        user = new User(id, name, email, lName);
    }

    @Test
    public void testConstructor_NullId_ThrowsIllegalArgumentException() {
        when(id).thenReturn(null);
        assertThatThrownBy(() -> new User(id, name, email, lName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("ID must not be null");
    }

    @Test
    public void testConstructor_NullName_ThrowsIllegalArgumentException() {
        when(name).thenReturn(null);
        assertThatThrownBy(() -> new User(id, name, email, lName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Name must not be null");
    }

    @Test
    public void testConstructor_NullEmail_ThrowsIllegalArgumentException() {
        when(email).thenReturn(null);
        assertThatThrownBy(() -> new User(id, name, email, lName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Email must not be null");
    }

    @Test
    public void testConstructor_NullLName_ThrowsIllegalArgumentException() {
        when(lName).thenReturn(null);
        assertThatThrownBy(() -> new User(id, name, email, lName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Last Name must not be null");
    }

    @Test
    public void testConstructor_InvalidEmailFormat_ThrowsIllegalArgumentException() {
        when(email).thenReturn("invalid-email");
        assertThatThrownBy(() -> new User(id, name, email, lName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid email format");
    }

    @Test
    public void testConstructor_ValidInput_SetsUserPropertiesCorrectly() {
        when(id).thenReturn(1L);
        when(name).thenReturn("John");
        when(email).thenReturn("john.doe@example.com");
        when(lName).thenReturn("Doe");

        User user = new User(id, name, email, lName);

        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getName()).isEqualTo("John");
        assertThat(user.getEmail()).isEqualTo("john.doe@example.com");
        assertThat(user.getLName()).isEqualTo("Doe");
    }

    @Test
    public void testSetEmail_NullInput_ThrowsIllegalArgumentException() {
        user.setEmail(null);
        assertThatThrownBy(() -> user.setEmail(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Email must not be null");
    }

    @Test
    public void testSetEmail_InvalidEmailFormat_ThrowsIllegalArgumentException() {
        user.setEmail("invalid-email");
        assertThatThrownBy(() -> user.setEmail("invalid-email"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Invalid email format");
    }

    @Test
    public void testSetEmail_ValidInput_SetsUserEmailCorrectly() {
        user.setEmail("john.doe@example.com");
        assertThat(user.getEmail()).isEqualTo("john.doe@example.com");
    }

    @Test
    public void testSetLName_EmptyInput_ThrowsIllegalArgumentException() {
        user.setLName("");
        assertThatThrownBy(() -> user.setLName(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Last name must not be empty");
    }

    @Test
    public void testSetLName_LengthGreaterThan100Characters_ThrowsIllegalArgumentException() {
        String longLName = "LongLastNameThatExceedsTheMaximumLengthOf100Characters";
        user.setLName(longLName);
        verify(user, times(1)).setLName(longLName); // This check verifies that the setter was called with the long last name
        assertThatThrownBy(() -> user.setLName(longLName))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Last name must not exceed 100 characters");
    }

    @Test
    public void testSetLName_ValidInput_SetsUserLNameCorrectly() {
        user.setLName("Doe");
        assertThat(user.getLName()).isEqualTo("Doe");
    }
}
```