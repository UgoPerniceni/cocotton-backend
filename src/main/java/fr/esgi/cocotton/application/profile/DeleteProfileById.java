package fr.esgi.cocotton.application.profile;
import fr.esgi.cocotton.domain.models.profile.ProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteProfileById {
    private final ProfileDao userDao;
    private final FindProfileById findByIdUser;

    @Autowired
    public DeleteProfileById(ProfileDao userDao, FindProfileById findByIdUser){
        this.userDao = userDao;
        this.findByIdUser = findByIdUser;
    }

    public void execute(String id){
        findByIdUser.execute(id);
        userDao.deleteById(id);
    }
}
