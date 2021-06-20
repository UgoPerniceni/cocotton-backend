package fr.esgi.cocotton.common.security;

import fr.esgi.cocotton.profile.application.FindProfileByUsername;
import fr.esgi.cocotton.profile.domain.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class DomainUserDetailsService implements UserDetailsService {

    private final FindProfileByUsername findUserByUsername;

    public DomainUserDetailsService(FindProfileByUsername findUserByUsername) {
        this.findUserByUsername = findUserByUsername;
    }

    public UserDetails loadUserByUsername(String username) {

        Profile profile = findUserByUsername.execute(username);

        return User.builder()
                .username(username)
                .password(profile.getPassword())
                .roles("USER")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
