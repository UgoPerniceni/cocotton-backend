package fr.esgi.cocotton.application.profile;

import fr.esgi.cocotton.domain.models.profile.Profile;
import fr.esgi.cocotton.domain.models.profile.ProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllProfiles {
    private final ProfileDao userDao;

    @Autowired
    public FindAllProfiles(ProfileDao userDao){
        this.userDao = userDao;
    }

    public List<Profile> execute(){
        return userDao.findAll();
    }
}
