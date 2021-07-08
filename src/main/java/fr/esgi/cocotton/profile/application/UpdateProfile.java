package fr.esgi.cocotton.profile.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import fr.esgi.cocotton.profile.domain.Profile;
import fr.esgi.cocotton.profile.domain.ProfileDao;
import org.springframework.stereotype.Service;

@Service
public class UpdateProfile {

    private final ProfileDao profileDao;
    private final FindProfileById findProfileById;

    public UpdateProfile(ProfileDao profileDao, FindProfileById findProfileById) {
        this.profileDao = profileDao;
        this.findProfileById = findProfileById;
    }

    public String execute(JsonPatch patch, String id) {
        Profile patchProfile = findProfileById.execute(id);
        System.out.println(patchProfile);
        try {
            patchProfile =  profileDao.applyPatch(patch, patchProfile);
        } catch (JsonPatchException | JsonProcessingException e) {
            return id;
        }
        return profileDao.save(patchProfile);
    }
}
