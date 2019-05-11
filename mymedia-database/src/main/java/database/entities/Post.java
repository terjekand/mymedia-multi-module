package database.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

@Getter
@Setter
public class Post implements Serializable {

    @Id
    private String id;

    private String userId;

    private String userName;

    private Date postDate;

    private String text;

    private String likers;

    public Post() {
        id = UUID.randomUUID().toString();
    }

    public Post(String userId, String text) {
        id = UUID.randomUUID().toString();
        this.userId = userId;
        java.util.Date date = new java.util.Date();
        postDate = new Date(date.getTime());
        this.text = text;
        likers = "";
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", postDate=" + postDate +
                ", text='" + text + '\'' +
                ", likers=" + likers +
                '}';
    }
}
