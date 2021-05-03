package fr.esgi.cocotton.domain.models.comment;

import fr.esgi.cocotton.domain.models.profile.Profile;

public class Comment {
    private String id;
    private String title;
    private String content;
    private Profile user;

    public Comment(String id, String title, String content) {
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

    public Profile getProfile() {
        return user;
    }

    public void setProfile(Profile user) {
        this.user = user;
    }
}
