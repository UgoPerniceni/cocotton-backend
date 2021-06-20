package fr.esgi.cocotton.profile.application;

import fr.esgi.cocotton.common.exception.ResourceNotFoundException;
import fr.esgi.cocotton.profile.domain.Profile;
import fr.esgi.cocotton.profile.domain.ProfileDao;
import org.springframework.stereotype.Service;

@Service
public class FindProfileById {

    private final ProfileDao profileDao;

    public FindProfileById(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    public Profile execute(String id) {
        return profileDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("profile", "id", id));
    }
}
