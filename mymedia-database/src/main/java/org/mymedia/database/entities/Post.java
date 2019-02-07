/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mymedia.database.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Posts")

@NamedQueries({
        @NamedQuery(name = "Post.getPostsOfUser", query = "SELECT u FROM Post u WHERE u.user =:un"),
})
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false, updatable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ENTITY", nullable = false, updatable = false)
    private User user;

    @Column(name = "POST_DATE", nullable = false, updatable = false)
    private LocalDate postDate;

    @Column(name = "TEXT", nullable = false, updatable = true)
    private String text;

    @JoinColumn(name = "LIKERS")
    @OneToMany
    private List<User> likers;

    public Post() {
    }

    public Post(User user, String text) {
        this.user = user;
        postDate = LocalDate.now();
        this.text = text;
        likers = new ArrayList<User>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<User> getLikers() {
        return likers;
    }

    public void setLikers(List<User> likers) {
        this.likers = likers;
    }


}
