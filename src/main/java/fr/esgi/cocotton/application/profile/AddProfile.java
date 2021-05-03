package fr.esgi.cocotton.application.profile;

import fr.esgi.cocotton.domain.models.profile.Profile;
import fr.esgi.cocotton.domain.models.profile.ProfileDao;
import org.springframework.stereotype.Service;

@Service
public class AddProfile {
    private final ProfileDao userDao;

    public AddProfile(ProfileDao userDao){
        this.userDao = userDao;
    }

    public String execute(Profile user) {
        return userDao.save(user);
    }
}
