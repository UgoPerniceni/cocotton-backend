package fr.esgi.cocotton.application.services.user;

import fr.esgi.cocotton.domain.models.user.User;
import fr.esgi.cocotton.domain.models.user.UserDao;
import org.springframework.stereotype.Service;

@Service
public class AddUser {
    private final UserDao userDao;

    public AddUser(UserDao userDao){
        this.userDao = userDao;
    }

    public String execute(User user) {
        return userDao.save(user);
    }
}
