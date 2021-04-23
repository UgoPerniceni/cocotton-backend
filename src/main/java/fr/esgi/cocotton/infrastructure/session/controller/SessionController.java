package fr.esgi.cocotton.infrastructure.session.controller;

import fr.esgi.cocotton.application.session.FindAllSessions;
import fr.esgi.cocotton.domain.models.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/sessions")
public class SessionController {

    private final FindAllSessions findAllSessions;

    @Autowired
    public SessionController(FindAllSessions findAllSessions){
        this.findAllSessions = findAllSessions;
    }

    @GetMapping
    public ResponseEntity<List<Session>> findAll(){
        return new ResponseEntity<>(findAllSessions.execute(), HttpStatus.OK);
    }
}

