/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mymedia.database.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.mymedia.database.entities.Post;
import org.mymedia.database.entities.User;

@Slf4j
public class PostDataBase {
    public static final PostDataBase DB_PELDANY = new PostDataBase();
    @PersistenceContext(unitName = "UsersDB")
    private EntityManager em;
    private PostDataBase(){
        
    }
    
    public static PostDataBase getDataBase(){
        return DB_PELDANY;
    }
   
    public void connectDB() throws Exception{
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("UsersDB");
        em = emFactory.createEntityManager();
        log.trace("Connected!");
    }
    
    public boolean connected(){
        if(em != null && em.isOpen())
            return true;
        return false;
    }
    
    public void disconnectDB(){
        if(connected()){
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
    
    public List<Post> getPostsOfUser(User user){
        if(!connected()){
            throw new IllegalStateException("Nincs adatbázis-kapcsolat");
        }
        try{
            Query query = em.createNamedQuery("Post.getPostsOfUser", Post.class);
            query.setParameter("un", user);
            @SuppressWarnings("unchecked")
            List<Post> posts = new ArrayList<Post>();
            posts = query.getResultList();
            return posts;
        }catch(Exception e){
            log.error("" + e);
            return null;
        }
    }
    
    
}
