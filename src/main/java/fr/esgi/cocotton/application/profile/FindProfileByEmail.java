package fr.esgi.cocotton.application.profile;

import fr.esgi.cocotton.domain.models.profile.Profile;
import fr.esgi.cocotton.domain.models.profile.ProfileDao;
import fr.esgi.cocotton.infrastructure.common.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FindProfileByEmail {

    private final ProfileDao userDao;

    public FindProfileByEmail(ProfileDao userDao){
        this.userDao = userDao;
    }

    public Profile execute(String email) {
        return userDao.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("user", email));
    }
}
