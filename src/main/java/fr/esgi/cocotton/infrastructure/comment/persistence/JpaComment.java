package fr.esgi.cocotton.infrastructure.comment.persistence;


import fr.esgi.cocotton.infrastructure.user.persistance.JpaUser;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "comment")
public class JpaComment {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false, nullable = false)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    private JpaUser user;

    public JpaComment(){}

    public JpaComment(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public JpaUser getUser() {
        return user;
    }

    public void setUser(JpaUser user) {
        this.user = user;
    }
}
