package fr.esgi.cocotton.domain.models.session;

import fr.esgi.cocotton.domain.models.user.User;

import java.time.LocalDateTime;

public class Session {

    private String id;
    private LocalDateTime createdAt;
    private String token;
    private User user;

    public Session(String id, LocalDateTime createdAt, String token, User user){
        this.id = id;
        this.createdAt = createdAt;
        this.token = token;
        this.user = user;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
