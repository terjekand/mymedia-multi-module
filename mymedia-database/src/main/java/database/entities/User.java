package database.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class User implements Serializable {

    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private String fullname;
    private String bio;
    private String phoneNumber;

    public User(String username, String password, String email, String fullname) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.bio = null;
        this.phoneNumber = null;
    }

    public User() {
    }


    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
                + ", fullname=" + fullname + "]";
    }

}
