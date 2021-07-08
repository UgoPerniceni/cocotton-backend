package fr.esgi.cocotton.profile.infrastructure.persistence;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import fr.esgi.cocotton.common.mapper.ProfileMapper;
import fr.esgi.cocotton.profile.domain.Profile;
import fr.esgi.cocotton.profile.domain.ProfileDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaProfileDao implements ProfileDao {

    private final JpaProfileRepository repository;
    private final ProfileMapper profileMapper;

    @Autowired
    public JpaProfileDao(JpaProfileRepository repository, ProfileMapper profileMapper){
        this.repository = repository;
        this.profileMapper = profileMapper;
    }

    public List<Profile> findAll(){
        return repository.findAll()
                .stream()
                .map(profileMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Optional<Profile> findById(String id){
        Optional<JpaProfile> jpaProfile = repository.findById(id);
        return jpaProfile.map(profileMapper::toDomain);
    }

    public Optional<Profile> findByEmail(String email){
        Optional<JpaProfile> jpaProfile = repository.findByEmail(email);
        return jpaProfile.map(profileMapper::toDomain);
    }

    public Optional<Profile> findByUsername(String username) {
        Optional<JpaProfile> jpaProfile = repository.findByUsername(username);
        return jpaProfile.map(profileMapper::toDomain);
    }

    public String save(Profile user){
        JpaProfile jpaProfile = profileMapper.toEntity(user);
        repository.save(jpaProfile);

        return jpaProfile.getId();
    }

    @Transactional
    public void deleteById(String id){
        repository.deleteById(id);
    }

    @Override
    public Profile applyPatch(JsonPatch patch, Profile targetProfile) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        JsonNode patched = patch.apply(objectMapper.convertValue(targetProfile, JsonNode.class));
        return objectMapper.treeToValue(patched, Profile.class);
    }
}
