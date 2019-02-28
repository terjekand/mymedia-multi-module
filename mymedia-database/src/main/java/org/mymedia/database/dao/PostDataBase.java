/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mymedia.database.dao;

import lombok.extern.slf4j.Slf4j;
import org.mymedia.database.entities.Post;
import org.mymedia.database.entities.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PostDataBase {
    private static final PostDataBase DB_PELDANY = new PostDataBase();
    @PersistenceContext(unitName = "UsersDB")
    private EntityManager em;

    private PostDataBase() {

    }

    public static PostDataBase getDataBase() {
        return DB_PELDANY;
    }

    public void connectDB() throws Exception {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("UsersDB");
        em = emFactory.createEntityManager();
        log.trace("Connected!");
    }

    public boolean connected() {
        if (em != null && em.isOpen())
            return true;
        return false;
    }

    public void disconnectDB() {
        if (connected()) {
            em.close();
            log.trace("Disconnected!");
        }
        em = null;
    }

    public Post save(Post entity) throws IllegalStateException, IllegalArgumentException, Exception {

        if (!connected()) {
            throw new IllegalStateException("No database connecton!");
        }

        if (entity == null) {
            throw new IllegalArgumentException("Null entity!");
        }

        try {
            em.getTransaction().begin();

            if (entity.getId() == null) {
                em.persist(entity);
            } else {
                em.merge(entity);
            }

            em.getTransaction().commit();

            return entity;
        } catch (PersistenceException e) {
            throw new Exception("JPA error!", e);
        }
    }

    public void delete(Post entity) throws IllegalStateException, IllegalArgumentException, Exception {
        if (!connected()) {
            throw new IllegalStateException("No database connecton!");
        }

        if (entity == null || entity.getId() == 0) {
            throw new IllegalArgumentException("Null entitiy vagy nincs id-je");
        }

        try {

            User delEntity = em.find(User.class, entity.getId());

            if (delEntity.getId() == null) {
                throw new IllegalArgumentException("A torlendo entitas nincs az adatbazisban");
            }

            em.getTransaction().begin();
            em.remove(delEntity);
            em.getTransaction().commit();

        } catch (PersistenceException e) {
            throw new Exception("JPA hiba", e);
        }
    }

    public List<Post> getPostsOfUserByUserId(String userId) {
        if (!connected()) {
            throw new IllegalStateException("Nincs adatbázis-kapcsolat");
        }
        try {
            Query query = em.createNamedQuery("Post.getPostsOfUser", Post.class);
            query.setParameter("id", userId);
            @SuppressWarnings("unchecked")
            List<Post> posts = new ArrayList<Post>();
            posts = query.getResultList();
            return posts;
        } catch (Exception e) {
            log.error("" + e);
            return null;
        }
    }

    public List<User> getLikersOfPostByPostId(Long postId) {
        if (!connected()) {
            throw new IllegalStateException("Nincs adatbázis-kapcsolat");
        }
        try {
            Query query = em.createNamedQuery("Post.getLikersOfPost", Post.class);
            query.setParameter("pId", postId);
            @SuppressWarnings("unchecked")
            List likers;
            likers = (ArrayList<User>)query.getResultList();
            return likers;
        } catch (Exception e) {
            log.error("" + e);
            return null;
        }
    }

    public Post getPostByPostId(Long postId){
        if (!connected()) {
            throw new IllegalStateException("Nincs adatbázis-kapcsolat");
        }
        try {
            Query query = em.createNamedQuery("Post.getPostByPostId", Post.class);
            query.setParameter("id", postId);
            @SuppressWarnings("unchecked")
            Post post = (Post)query.getSingleResult();
            return post;
        } catch (Exception e) {
            log.error("" + e);
            return null;
        }
    }

}
