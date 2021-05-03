package fr.esgi.cocotton.application.profile;

import fr.esgi.cocotton.domain.models.profile.Profile;
import fr.esgi.cocotton.domain.models.profile.ProfileDao;
import fr.esgi.cocotton.infrastructure.common.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindProfileById {
    private final ProfileDao userDao;

    @Autowired
    public FindProfileById(ProfileDao userDao){
        this.userDao = userDao;
    }

    public Profile execute(String id) {
        return userDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", id));
    }
}
