package fr.esgi.cocotton.profile.application;

import fr.esgi.cocotton.common.exception.ResourceNotFoundException;
import fr.esgi.cocotton.profile.domain.Profile;
import fr.esgi.cocotton.profile.domain.ProfileDao;
import org.springframework.stereotype.Service;

@Service
public class FindProfileByUsername {

    private final ProfileDao profileDao;

    public FindProfileByUsername(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    public Profile execute(String username) {
        return profileDao.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("profile", "username", username));
    }
}
