package fr.esgi.cocotton.comment.domain;

public class Comment {

    private String id;
    private String content;
    private String userId;

    public Comment(String id, String content, String userId) {
        this.id = id;
        this.content = content;
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}