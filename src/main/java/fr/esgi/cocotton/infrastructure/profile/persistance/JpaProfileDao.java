package fr.esgi.cocotton.infrastructure.profile.persistance;

import fr.esgi.cocotton.domain.models.profile.Profile;
import fr.esgi.cocotton.domain.models.profile.ProfileDao;
import fr.esgi.cocotton.infrastructure.common.mapper.ProfileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaProfileDao implements ProfileDao {
    private final JpaProfileRepository repository;
    private final ProfileMapper mapper;

    @Autowired
    public JpaProfileDao(JpaProfileRepository repository, ProfileMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Profile> findAll(){
        return repository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    public Optional<Profile> findById(String id){
        Optional<JpaProfile> jpaProfile = repository.findById(id);
        return jpaProfile.map(mapper::toDomain);
    }

    public Optional<Profile> findByEmail(String email){
        Optional<JpaProfile> jpaProfile = repository.findByEmail(email);
        return jpaProfile.map(mapper::toDomain);
    }

    public Optional<Profile> findByUsername(String username) {
        Optional<JpaProfile> jpaProfile = repository.findByUsername(username);
        return jpaProfile.map(mapper::toDomain);
    }

    public String save(Profile user){
        JpaProfile jpaProfile = mapper.toEntity(user);
        repository.save(jpaProfile);

        return jpaProfile.getId();
    }

    @Transactional
    public void deleteById(String id){
        repository.deleteById(id);
    }
}
