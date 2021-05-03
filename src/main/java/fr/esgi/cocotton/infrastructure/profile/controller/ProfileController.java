package fr.esgi.cocotton.infrastructure.profile.controller;

import fr.esgi.cocotton.application.profile.DeleteProfileById;
import fr.esgi.cocotton.application.profile.FindAllProfiles;
import fr.esgi.cocotton.application.profile.FindProfileById;
import fr.esgi.cocotton.domain.models.profile.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class ProfileController {

    private final FindAllProfiles findAllUser;
    private final FindProfileById findByIdUser;
    private final DeleteProfileById deleteUser;

    @Autowired
    public ProfileController(FindAllProfiles findAllUser, FindProfileById findByIdUser, DeleteProfileById deleteUser) {
        this.findAllUser = findAllUser;
        this.findByIdUser = findByIdUser;
        this.deleteUser = deleteUser;
    }

    @GetMapping
    public ResponseEntity<List<Profile>> findAll(){
        return new ResponseEntity<>(findAllUser.execute(), HttpStatus.OK);
    }

    @RolesAllowed("USER")
    @GetMapping("/{id}")
    public ResponseEntity<Profile> findById(@PathVariable String id){
        return new ResponseEntity<>(findByIdUser.execute(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        deleteUser.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
