package fr.esgi.cocotton.application.user;

import fr.esgi.cocotton.domain.models.user.User;
import fr.esgi.cocotton.domain.models.user.UserDao;
import fr.esgi.cocotton.infrastructure.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindByIdUser {
    private final UserDao userDao;

    @Autowired
    public FindByIdUser(UserDao userDao){
        this.userDao = userDao;
    }

    public User execute(String id) {
        return userDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", id));
    }
}
