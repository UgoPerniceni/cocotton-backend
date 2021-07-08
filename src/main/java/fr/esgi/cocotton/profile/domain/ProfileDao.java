package fr.esgi.cocotton.profile.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

import java.util.List;
import java.util.Optional;

public interface ProfileDao {

    List<Profile> findAll();

    Optional<Profile> findById(String id);

    Optional<Profile> findByEmail(String email);

    Optional<Profile> findByUsername(String username);

    String save(Profile profile);

    void deleteById(String id);
    
    Profile applyPatch(JsonPatch patch, Profile targetProfile) throws JsonPatchException, JsonProcessingException;
}
