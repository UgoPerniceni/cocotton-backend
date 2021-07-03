package fr.esgi.cocotton.comment.infrastructure.controller;

import fr.esgi.cocotton.comment.application.*;
import fr.esgi.cocotton.comment.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final FindAllComments findAllComments;
    private final FindAllCommentsByUserId findAllCommentsByUserId;
    private final FindCommentById findCommentById;
    private final AddComment addComment;
    private final DeleteCommentById deleteCommentById;

    @Autowired
    public CommentController(FindAllComments findAllComments,
                             FindAllCommentsByUserId findAllCommentsByUserId,
                             FindCommentById findCommentById,
                             AddComment addComment,
                             DeleteCommentById deleteCommentById) {
        this.findAllComments = findAllComments;
        this.findAllCommentsByUserId = findAllCommentsByUserId;
        this.findCommentById = findCommentById;
        this.addComment = addComment;
        this.deleteCommentById = deleteCommentById;
    }

    @GetMapping
    public ResponseEntity<List<Comment>> findAll() {
        return new ResponseEntity<>(findAllComments.execute(), HttpStatus.OK);
    }

    @GetMapping("/profiles/{userId}")
    public ResponseEntity<List<Comment>> findAllByUser(@PathVariable String userId) {
        return new ResponseEntity<>(findAllCommentsByUserId.execute(userId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> findById(@PathVariable String id) {
        return new ResponseEntity<>(findCommentById.execute(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Comment comment) {
        String id = addComment.execute(comment);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        deleteCommentById.execute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
