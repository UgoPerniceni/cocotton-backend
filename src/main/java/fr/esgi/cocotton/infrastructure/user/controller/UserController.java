package fr.esgi.cocotton.infrastructure.user.controller;

import fr.esgi.cocotton.application.services.user.AddUser;
import fr.esgi.cocotton.application.services.user.DeleteUser;
import fr.esgi.cocotton.application.services.user.FindAllUser;
import fr.esgi.cocotton.application.services.user.FindByIdUser;
import fr.esgi.cocotton.domain.models.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final FindAllUser findAllUser;
    private final FindByIdUser findByIdUser;
    private final AddUser addUser;
    private final DeleteUser deleteUser;

    @Autowired
    public UserController(FindAllUser findAllUser, FindByIdUser findByIdUser, AddUser addUser, DeleteUser deleteUser) {
        this.findAllUser = findAllUser;
        this.findByIdUser = findByIdUser;
        this.addUser = addUser;
        this.deleteUser = deleteUser;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        return new ResponseEntity<>(findAllUser.execute(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable String id){
        return new ResponseEntity<>(findByIdUser.execute(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user){
        String id = addUser.execute(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        deleteUser.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
