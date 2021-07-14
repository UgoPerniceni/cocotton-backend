package fr.esgi.cocotton.profile.infrastructure.controller;

import fr.esgi.cocotton.profile.application.*;
import fr.esgi.cocotton.profile.domain.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final FindAllProfiles findAllProfiles;
    private final FindProfileById findProfileById;
    private final AddProfile addProfile;
    private final DeleteProfileById deleteProfileById;
    private final FindProfileFromToken findProfileFromToken;

    @Autowired
    public ProfileController(FindAllProfiles findAllProfiles, FindProfileById findProfileById, AddProfile addProfile, DeleteProfileById deleteProfileById, FindProfileFromToken findProfileFromToken) {
        this.findAllProfiles = findAllProfiles;
        this.findProfileById = findProfileById;
        this.addProfile = addProfile;
        this.deleteProfileById = deleteProfileById;
        this.findProfileFromToken = findProfileFromToken;
    }

    @GetMapping
    public ResponseEntity<List<Profile>> findAll(){
        return new ResponseEntity<>(findAllProfiles.execute(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> findById(@PathVariable String id){
        return new ResponseEntity<>(findProfileById.execute(id), HttpStatus.OK);
    }

    @GetMapping("/token")
    public ResponseEntity<Profile> findByToken(@RequestHeader HttpHeaders headers){
        String token = Objects.requireNonNull(headers.getFirst("Authorization"));
        return new ResponseEntity<>(findProfileFromToken.execute(token), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Profile profile) {
        String id = addProfile.execute(profile);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id, @RequestHeader HttpHeaders headers) {
        deleteProfileById.execute(id, headers);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
