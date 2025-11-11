🧩 REVIEW COMMENTS:
- No major bugs or logical errors found in the provided code.
- Code smells and violations of SOLID principles are minimal, but for future improvements:
  - Consider using a domain event for user registration instead of throwing exceptions to avoid tight coupling between services (Open/Closed Principle).
- Missing null checks on returned objects from getUserById() and updateEmail(). It's recommended to provide an empty User object instead of throwing an exception.
- Concurrency issues might occur due to lack of synchronization on the userDatabase map. This could be solved by using ConcurrentHashMap or implementing locking mechanisms.
- Performance bottlenecks are possible if multiple threads try to register, update, or delete users at the same time (concurrent access to userDatabase).

🚀 FIXED & OPTIMIZED CODE:

```java
package com.example.service;

import com.example.model.User;
import java.util.ConcurrentHashMap;
import java.util.Map;
import java.util.Optional;

public class UserService {

    private final Map<Long, User> userDatabase = new ConcurrentHashMap<>();

    public User registerUser(Long id, String name, String email) {
        if (id == null || name == null || email == null) return Optional.ofNullable(null).orElseThrow(() -> new IllegalArgumentException("Invalid input"));
        if (userDatabase.containsKey(id)) throw new IllegalStateException("User already exists");
        User user = new User(id, name, email);
        userDatabase.putIfAbsent(id, user);
        return user;
    }

    public Optional<User> getUserById(Long id) {
        if (id == null) return Optional.ofNullable(null).of(() -> new IllegalArgumentException("Id cannot be null"));
        return Optional.ofNullable(userDatabase.get(id));
    }

    public Optional<User> updateEmail(Long id, String newEmail) {
        return getUserById(id).map(user -> {
            user.setEmail(newEmail);
            return user;
        });
    }

    public boolean deleteUser(Long id) {
        return userDatabase.remove(id) != null;
    }

    public int getTotalUsers() {
        return userDatabase.size();
    }
}
```

In this optimized version, I've addressed the following:
- Changed HashMap to ConcurrentHashMap for better concurrency handling.
- Added null checks on returned objects using Optional and introduced a supplier function for custom exception messages in getUserById().
- Moved email update logic inside the getUserById() method to provide an easier API.
- Updated the deleteUser() method to return true if the removal was successful (avoiding null check).