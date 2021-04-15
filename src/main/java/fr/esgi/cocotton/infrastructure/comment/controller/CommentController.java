package fr.esgi.cocotton.infrastructure.comment.controller;

import fr.esgi.cocotton.application.comment.AddComment;
import fr.esgi.cocotton.application.comment.DeleteComment;
import fr.esgi.cocotton.application.comment.FindAllComment;
import fr.esgi.cocotton.application.comment.FindByIdComment;
import fr.esgi.cocotton.domain.models.comment.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final FindAllComment findAllComment;
    private final FindByIdComment findByIdComment;
    private final AddComment addComment;
    private final DeleteComment deleteComment;

    @Autowired
    public CommentController(FindAllComment findAllComment, FindByIdComment findByIdComment, AddComment addComment, DeleteComment deleteComment) {
        this.findAllComment = findAllComment;
        this.findByIdComment = findByIdComment;
        this.addComment = addComment;
        this.deleteComment = deleteComment;
    }

    @GetMapping
    public ResponseEntity<List<Comment>> findAll(){
        return new ResponseEntity<>(findAllComment.execute(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> findById(@PathVariable String id){
        return new ResponseEntity<>(findByIdComment.execute(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Comment comment){
        String id = addComment.execute(comment);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        deleteComment.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
