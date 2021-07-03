package fr.esgi.cocotton.profile.application;

import fr.esgi.cocotton.profile.domain.Profile;
import fr.esgi.cocotton.profile.domain.ProfileDao;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllProfiles {

    private final ProfileDao profileDao;

    public FindAllProfiles(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    public List<Profile> execute() {
        return profileDao.findAll();
    }
}
