package fr.esgi.cocotton.application.profile;

import fr.esgi.cocotton.domain.models.profile.Profile;
import fr.esgi.cocotton.domain.models.profile.ProfileDao;
import fr.esgi.cocotton.infrastructure.common.exception.ProfileWithUsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindProfileByUsername {

    private final ProfileDao userDao;

    @Autowired
    public FindProfileByUsername(ProfileDao userDao) {
        this.userDao = userDao;
    }

    public Profile execute(String username) {
        return userDao.findByUsername(username)
                .orElseThrow(() -> new ProfileWithUsernameNotFoundException(username));
    }
}
