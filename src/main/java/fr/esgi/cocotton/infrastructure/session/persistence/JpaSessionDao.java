package fr.esgi.cocotton.infrastructure.session.persistence;

import fr.esgi.cocotton.domain.models.session.Session;
import fr.esgi.cocotton.domain.models.session.SessionDao;
import fr.esgi.cocotton.domain.models.user.User;
import fr.esgi.cocotton.infrastructure.common.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class JpaSessionDao implements SessionDao {

    private final JpaSessionRepository repository;
    private final UserMapper userMapper;

    @Autowired
    public JpaSessionDao(JpaSessionRepository repository, UserMapper userMapper){
        this.repository = repository;
        this.userMapper = userMapper;
    }

    public List<Session> findAll(){
        return repository.findAll()
                .stream()
                .map(jpaSession ->
                        new Session(jpaSession.getId(),
                                jpaSession.getCreatedAt(),
                                jpaSession.getToken(),
                                userMapper.toDomain(jpaSession.getJpaUser())))
                .collect(Collectors.toList());
    }

    public List<Session> findAllByUser(User user){
        return repository.findAllByJpaUser(userMapper.toEntity(user))
                .stream()
                .map(jpaSession ->
                        new Session(jpaSession.getId(),
                                jpaSession.getCreatedAt(),
                                jpaSession.getToken(),
                                user
                                ))
                .collect(Collectors.toList());
    }

    public Optional<Session> findByToken(String token){
        return repository.findByToken(token)
                .map(jpaSession ->
                        new Session(jpaSession.getId(),
                                jpaSession.getCreatedAt(),
                                jpaSession.getToken(),
                                userMapper.toDomain(jpaSession.getJpaUser()))
                );
    }

    public void save(Session session){
        JpaSession jpaSession = new JpaSession(session.getToken(), userMapper.toEntity(session.getUser()));
        repository.save(jpaSession);
    }
}
