package dtos;

import java.util.Objects;

public class User {

    private long id;
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private long securityQuestionId;
    private String answer;

    public User(long id, String username, String password, String firstname, String lastname, String email) {
        this(username, password, firstname, lastname, email);
        this.id = id;
    }

    public User(String username, String password, String firstname, String lastname, String email) {
        this(username, password);
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return firstname + " " + lastname;
    }

    public long getSecurityQuestionId() {
        return securityQuestionId;
    }

    public void setSecurityQuestionId(long securityQuestionId) {
        this.securityQuestionId = securityQuestionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
    
    @Override
    public String toString() {
        return "Usuario {id:" + id
                + ", usuario:" + username
                + ", contrasena:" + password + "}";
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        User usr = (User) o;
        return this.username.equals(usr.username);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.username);
        return hash;
    }

}
