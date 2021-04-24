package fr.esgi.cocotton.infrastructure.common.mapper;

import fr.esgi.cocotton.domain.enums.role.Role;
import fr.esgi.cocotton.domain.models.user.User;
import fr.esgi.cocotton.infrastructure.user.persistance.JpaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapper implements ObjectMapper<User, JpaUser> {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserMapper(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    public User toDomain(JpaUser jpaUser) {
        return new User(jpaUser.getId(),
                jpaUser.getFirstName(),
                jpaUser.getLastName(),
                jpaUser.getEmail(),
                jpaUser.getPassword(),
                jpaUser.getGender(),
                jpaUser.getBirthDate(),
                List.of(Role.USER)
        );
    }

    public JpaUser toEntity(User user) {
        return new JpaUser(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                passwordEncoder.encode(user.getPassword()),
                user.getGender(),
                user.getBirthDate(),
                user.getRoles()
        );
    }
}
