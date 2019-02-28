/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mymedia.database.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "Posts")

@NamedQueries({
        @NamedQuery(name = "Post.getPostsOfUser", query = "SELECT u FROM Post u WHERE u.userId =:id"),
        @NamedQuery(name = "Post.getLikersOfPost", query = "SELECT u.likers FROM Post u WHERE u.id =:pId"),
        @NamedQuery(name = "Post.getPostByPostId", query = "SELECT u FROM Post u WHERE u.id =:id"),
})
@Getter
@Setter
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @JoinColumn(name = "USER_ID", nullable = false, updatable = false)
    private String userId;

    @Column(name = "POST_DATE", nullable = false, updatable = false)
    private LocalDate postDate;

    @Column(name = "TEXT", nullable = false)
    private String text;

    @Column(name = "LIKERS")
    private String likers;

    public Post() {
    }

    public Post(String userId, String text) {
        this.userId = userId;
        postDate = LocalDate.now();
        this.text = text;
        likers = null;
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
