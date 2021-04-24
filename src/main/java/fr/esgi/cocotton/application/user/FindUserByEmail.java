package fr.esgi.cocotton.application.user;

import fr.esgi.cocotton.domain.models.user.User;
import fr.esgi.cocotton.domain.models.user.UserDao;
import fr.esgi.cocotton.infrastructure.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FindUserByEmail {

    private final UserDao userDao;

    public FindUserByEmail(UserDao userDao){
        this.userDao = userDao;
    }

    public User execute(String email) {
        return userDao.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("user", email));
    }
}
