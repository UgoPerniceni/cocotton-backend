package fr.esgi.cocotton.common.mapper;

import fr.esgi.cocotton.profile.domain.Profile;
import fr.esgi.cocotton.profile.infrastructure.persistence.JpaProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ProfileMapper implements ObjectMapper<Profile, JpaProfile> {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ProfileMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Profile toDomain(JpaProfile jpaProfile) {
        return new Profile(jpaProfile.getId(),
                jpaProfile.getLastName(),
                jpaProfile.getFirstName(),
                jpaProfile.getUsername(),
                jpaProfile.getEmail(),
                jpaProfile.getPassword(),
                jpaProfile.getBirthDate()
        );
    }

    public JpaProfile toEntity(Profile user) {
        return new JpaProfile(user.getId(),
                user.getLastName(),
                user.getFirstName(),
                user.getUsername(),
                user.getEmail(),
                passwordEncoder.encode(user.getPassword()),
                user.getBirthDate()
        );
    }
}
