package fr.esgi.cocotton.infrastructure.session.persistence;

import fr.esgi.cocotton.infrastructure.profile.persistance.JpaProfile;
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
    private JpaProfile jpaProfile;


    public JpaSession(String token, JpaProfile jpaProfile){
        this.createdAt = LocalDateTime.now();
        this.token = token;
        this.jpaProfile = jpaProfile;
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

    public JpaProfile getJpaProfile() {
        return jpaProfile;
    }

    public void setJpaProfile(JpaProfile jpaProfile) {
        this.jpaProfile = jpaProfile;
    }
}
