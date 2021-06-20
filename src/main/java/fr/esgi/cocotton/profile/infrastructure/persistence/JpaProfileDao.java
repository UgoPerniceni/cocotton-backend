package fr.esgi.cocotton.profile.infrastructure.persistence;

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
}
