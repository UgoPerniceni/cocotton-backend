package fr.esgi.cocotton.session.infrastructure.controller;

import fr.esgi.cocotton.session.application.FindAllSessions;
import fr.esgi.cocotton.session.application.FindAllSessionsByUserId;
import fr.esgi.cocotton.session.application.FindLastSessionByUserId;
import fr.esgi.cocotton.session.application.FindSessionById;
import fr.esgi.cocotton.session.domain.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    private final FindAllSessions findAllSessions;
    private final FindAllSessionsByUserId findAllSessionsByUserId;
    private final FindLastSessionByUserId findLastSessionByUserId;
    private final FindSessionById findSessionById;

    @Autowired
    public SessionController(FindAllSessions findAllSessions, FindAllSessionsByUserId findAllSessionsByUserId, FindLastSessionByUserId findLastSessionByUserId, FindSessionById findSessionById){
        this.findAllSessions = findAllSessions;
        this.findAllSessionsByUserId = findAllSessionsByUserId;
        this.findLastSessionByUserId = findLastSessionByUserId;
        this.findSessionById = findSessionById;
    }

    @GetMapping
    public ResponseEntity<List<Session>> findAll(){
        return new ResponseEntity<>(findAllSessions.execute(), HttpStatus.OK);
    }

    @GetMapping("/profiles/{userId}")
    public ResponseEntity<List<Session>> findAllByUserId(@PathVariable String userId){
        return new ResponseEntity<>(findAllSessionsByUserId.execute(userId), HttpStatus.OK);
    }

    @GetMapping("/profiles/{userId}/last")
    public ResponseEntity<Session> findLastByUserId(@PathVariable String userId){
        return new ResponseEntity<>(findLastSessionByUserId.execute(userId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Session> findById(@PathVariable String id){
        return new ResponseEntity<>(findSessionById.execute(id), HttpStatus.OK);
    }
}
