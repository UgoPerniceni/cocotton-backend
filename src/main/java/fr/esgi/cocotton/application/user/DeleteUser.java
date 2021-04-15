package fr.esgi.cocotton.application.user;
import fr.esgi.cocotton.domain.models.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteUser {
    private final UserDao userDao;
    private final FindByIdUser findByIdUser;

    @Autowired
    public DeleteUser(UserDao userDao, FindByIdUser findByIdUser){
        this.userDao = userDao;
        this.findByIdUser = findByIdUser;
    }

    public void execute(String id){
        findByIdUser.execute(id);
        userDao.deleteById(id);
    }
}
