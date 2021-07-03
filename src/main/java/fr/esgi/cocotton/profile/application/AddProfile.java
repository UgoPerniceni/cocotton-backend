package fr.esgi.cocotton.profile.application;

import fr.esgi.cocotton.profile.domain.Profile;
import fr.esgi.cocotton.profile.domain.ProfileDao;
import org.springframework.stereotype.Service;

@Service
public class AddProfile {

    private final ProfileDao profileDao;

    public AddProfile(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    public String execute(Profile profile) {
        return profileDao.save(profile);
    }
}
