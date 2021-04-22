package fr.esgi.cocotton.infrastructure.user.controller;

import fr.esgi.cocotton.application.user.DeleteUser;
import fr.esgi.cocotton.application.user.FindAllUser;
import fr.esgi.cocotton.application.user.FindByIdUser;
import fr.esgi.cocotton.domain.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final FindAllUser findAllUser;
    private final FindByIdUser findByIdUser;
    private final DeleteUser deleteUser;

    @Autowired
    public UserController(FindAllUser findAllUser, FindByIdUser findByIdUser, DeleteUser deleteUser) {
        this.findAllUser = findAllUser;
        this.findByIdUser = findByIdUser;
        this.deleteUser = deleteUser;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return new ResponseEntity<>(findAllUser.execute(), HttpStatus.OK);
    }

    @RolesAllowed("USER")
    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable String id){
        return new ResponseEntity<>(findByIdUser.execute(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        deleteUser.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
