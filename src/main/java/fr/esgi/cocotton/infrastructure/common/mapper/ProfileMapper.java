package fr.esgi.cocotton.infrastructure.common.mapper;

import fr.esgi.cocotton.domain.enums.role.Role;
import fr.esgi.cocotton.domain.models.profile.Profile;
import fr.esgi.cocotton.infrastructure.profile.persistance.JpaProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileMapper implements ObjectMapper<Profile, JpaProfile> {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ProfileMapper(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }

    public Profile toDomain(JpaProfile jpaProfile) {
        return new Profile(jpaProfile.getId(),
                jpaProfile.getFirstName(),
                jpaProfile.getLastName(),
                jpaProfile.getUsername(),
                jpaProfile.getEmail(),
                jpaProfile.getPassword(),
                jpaProfile.getGender(),
                jpaProfile.getBirthDate(),
                List.of(Role.USER)
        );
    }

    public JpaProfile toEntity(Profile user) {
        return new JpaProfile(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getEmail(),
                passwordEncoder.encode(user.getPassword()),
                user.getGender(),
                user.getBirthDate(),
                user.getRoles()
        );
    }
}
