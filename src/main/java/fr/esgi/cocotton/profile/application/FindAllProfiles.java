package fr.esgi.cocotton.profile.application;

import fr.esgi.cocotton.profile.application.dto.ProfileDTO;
import fr.esgi.cocotton.profile.domain.Profile;
import fr.esgi.cocotton.profile.domain.ProfileDao;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindAllProfiles {

    private final ProfileDao profileDao;

    public FindAllProfiles(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }

    public List<ProfileDTO> execute() {
        List<ProfileDTO> profilesDTO = new ArrayList<>();
        List<Profile> profiles = profileDao.findAll();

        profiles.forEach(profile -> profilesDTO.add(profile.toProfileDTO()));

        return profilesDTO;
    }
}
