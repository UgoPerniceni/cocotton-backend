package fr.esgi.cocotton.domain.models.user;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> findAll();

    Optional<User> findById(String id);

    String save(User user);

    void deleteById(String id);
}
