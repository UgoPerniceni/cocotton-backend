package fr.esgi.cocotton.infrastructure.session.persistence;

import fr.esgi.cocotton.infrastructure.user.persistance.JpaUser;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity(name = "session")
public class JpaSession {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;

    private LocalDateTime createdAt;

    private String token;

    @ManyToOne
    private JpaUser jpaUser;


    public JpaSession(String token, JpaUser jpaUser){
        this.createdAt = LocalDateTime.now();
        this.token = token;
        this.jpaUser = jpaUser;
    }
    public JpaSession(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JpaUser getJpaUser() {
        return jpaUser;
    }

    public void setJpaUser(JpaUser jpaUser) {
        this.jpaUser = jpaUser;
    }
}
