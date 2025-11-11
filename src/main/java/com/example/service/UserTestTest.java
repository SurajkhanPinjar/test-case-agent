Your provided `UserTest` class is already well-structured and covers various scenarios such as valid input, edge cases (null values), and exception handling. However, I can suggest additional tests for methods that might be missing in your `UserService` class. Here's an example of how you could expand the test class with more mockito mocks and test methods:

```java
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

...

class UserTest {

    @Mock
    private User user;

    @InjectMocks
    private UserService userService;

    @Mock
    private EmailService emailService;

    @Mock
    private LastNameService lastNameService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(emailService, lastNameService); // Initialize UserService with mocked dependencies
    }

    ...

    @Test
    void testUserCreationWithExistingEmail() {
        Long id = 1L;
        String name = "John Doe";
        String email = "existing_email@example.com";
        when(userService.getUserByEmail(email)).thenReturn(Optional.of(user));

        assertThrows(IllegalArgumentException.class, () -> userService.createUser(id, name, email, "Doe"));
        verify(emailService).checkEmailAvailability(email); // Verify that checkEmailAvailability method is called
    }

    @Test
    void testUserCreationWithExistingLastName() {
        Long id = 1L;
        String name = "John Doe";
        String email = "john.doe@example.com";
        String lName = "existing_last_name";
        when(userService.getUserByLName(lName)).thenReturn(Optional.of(user));

        assertThrows(IllegalArgumentException.class, () -> userService.createUser(id, name, email, lName));
        verify(lastNameService).checkLastNameAvailability(lName); // Verify that checkLastNameAvailability method is called
    }

    ...
}
```

In the above example, we've added two new tests to check if the service throws an exception when trying to create a user with an existing email or last name. Additionally, I've mocked `EmailService` and `LastNameService` to test their respective methods: `checkEmailAvailability` and `checkLastNameAvailability`.

This way, you can continue testing other potential scenarios and edge cases as your application grows.