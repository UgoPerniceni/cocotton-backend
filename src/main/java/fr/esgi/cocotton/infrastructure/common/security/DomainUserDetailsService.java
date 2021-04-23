package fr.esgi.cocotton.infrastructure.common.security;

import fr.esgi.cocotton.infrastructure.user.persistance.JpaUser;
import fr.esgi.cocotton.infrastructure.user.persistance.JpaUserRepository;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DomainUserDetailsService implements UserDetailsService {

    private final JpaUserRepository repository;

    public DomainUserDetailsService(JpaUserRepository repository) {
        this.repository = repository;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        JpaUser user = repository.findByEmail(username).orElseThrow(() -> new AuthenticationServiceException("user not found"));

        return User.builder()
                .username(username)
                .password(user.getPassword())
                .roles("ADMIN")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
