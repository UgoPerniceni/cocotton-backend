package fr.esgi.cocotton.domain.models.user;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> findAll();

    Optional<User> findById(String id);

    Optional<User> findByEmail(String email);

    String save(User user);

    void deleteById(String id);
}
