package fr.esgi.cocotton.session.domain;

import java.time.LocalDateTime;

public class Session {

    private String id;
    private LocalDateTime createdAt;
    private String userId;

    public Session(String id, LocalDateTime createdAt, String userId) {
        this.id = id;
        this.createdAt = createdAt;
        this.userId = userId;
    }

    public Session(LocalDateTime createdAt, String userId) {
        this.createdAt = createdAt;
        this.userId = userId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
