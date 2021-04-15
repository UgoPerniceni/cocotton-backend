package fr.esgi.cocotton.application.user;

import fr.esgi.cocotton.domain.models.user.User;
import fr.esgi.cocotton.domain.models.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllUser {

    private final UserDao userDao;

    @Autowired
    public FindAllUser(UserDao userDao){
        this.userDao = userDao;
    }

    public List<User> execute(){
        return userDao.findAll();
    }
}
