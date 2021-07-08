package fr.esgi.cocotton.profile.infrastructure.controller;

import com.github.fge.jsonpatch.JsonPatch;
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

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final FindAllProfiles findAllProfiles;
    private final FindProfileById findProfileById;
    private final AddProfile addProfile;
    private final DeleteProfileById deleteProfileById;
    private final UpdateProfile updateProfile;

    @Autowired
    public ProfileController(FindAllProfiles findAllProfiles, FindProfileById findProfileById, AddProfile addProfile, DeleteProfileById deleteProfileById, UpdateProfile updateProfile) {
        this.findAllProfiles = findAllProfiles;
        this.findProfileById = findProfileById;
        this.addProfile = addProfile;
        this.deleteProfileById = deleteProfileById;
        this.updateProfile = updateProfile;
    }

    @GetMapping
    public ResponseEntity<List<Profile>> findAll(){
        return new ResponseEntity<>(findAllProfiles.execute(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> findById(@PathVariable String id){
        return new ResponseEntity<>(findProfileById.execute(id), HttpStatus.OK);
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

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<?> save(@PathVariable String id, @RequestBody JsonPatch patch) {
        updateProfile.execute(patch, id);
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
