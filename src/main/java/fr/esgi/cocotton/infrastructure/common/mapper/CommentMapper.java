package fr.esgi.cocotton.infrastructure.common.mapper;

import fr.esgi.cocotton.domain.models.comment.Comment;
import fr.esgi.cocotton.infrastructure.comment.persistence.JpaComment;

public class CommentMapper implements ObjectMapper<Comment, JpaComment> {

    public Comment toDomain(JpaComment jpaComment){
        return new Comment(jpaComment.getId(), jpaComment.getTitle(), jpaComment.getContent());
    }

    public JpaComment toEntity(Comment comment){
        return new JpaComment(comment.getId(), comment.getTitle(), comment.getContent());
    }
}
