package fr.esgi.cocotton.infrastructure.common.mapper;

import fr.esgi.cocotton.domain.models.user.User;
import fr.esgi.cocotton.infrastructure.user.persistance.JpaUser;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements ObjectMapper<User, JpaUser> {

    public User toDomain(JpaUser jpaUser) {
        return new User(jpaUser.getId(),
                jpaUser.getFirstName(),
                jpaUser.getLastName(),
                jpaUser.getEmail(),
                jpaUser.getPassword(),
                jpaUser.getGender(),
                jpaUser.getBirthDate()
        );
    }

    public JpaUser toEntity(User user) {
        return new JpaUser(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getGender(),
                user.getBirthDate()
        );
    }
}
