package fr.esgi.cocotton.domain.models.profile;

import fr.esgi.cocotton.domain.enums.role.Role;
import fr.esgi.cocotton.domain.models.comment.Comment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Profile {
    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private String gender;
    private LocalDate birthDate;
    private List<Comment> comments;
    private List<Role> roles;

    public Profile(String id, String firstName, String lastName, String username, String email, String password, String gender, LocalDate birthDate, List<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
        this.roles = roles;
        comments = new ArrayList<>();
    }

    public Profile(String firstName, String lastName, String username, String email, String password, String gender, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void comment(Comment comment){
        comment.setProfile(this);
        this.getComments().add(comment);
    }
}
