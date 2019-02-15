/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mymedia.database.dao;

import lombok.extern.slf4j.Slf4j;
import org.mymedia.database.entities.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UserDataBase implements Serializable {
    private static final UserDataBase DB_PELDANY = new UserDataBase();
    @PersistenceContext(unitName = "UsersDB")
    private EntityManager em;

    private UserDataBase() {

    }

    public static UserDataBase getDataBase() {
        return DB_PELDANY;
    }

    public void connectDB() throws Exception {
        EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("UsersDB");
        em = emFactory.createEntityManager();
    }

    public boolean connected() {
        if (em != null && em.isOpen())
            return true;
        return false;
    }

    public void disconnectDB() {
        if (connected()) {
            em.close();
        }
        em = null;
    }

    public User save(User entity) throws IllegalStateException, IllegalArgumentException, Exception {

        if (!connected()) {
            throw new IllegalStateException("Nincs adatbazis kapcsolat!");
        }

        if (entity == null) {
            throw new IllegalArgumentException("A mentendo entitas null!");
        }

        try {
            em.getTransaction().begin();

            if (entity.getId() == null) {
                em.persist(entity);  //új entitás --> persist (insert)
            } else {
                em.merge(entity);    //módosítás --> merge (update)
            }

            em.getTransaction().commit();

            return entity;
        } catch (PersistenceException e) {
            throw new Exception("JPA hiba!", e);
        }
    }

    public void delete(User entity) throws IllegalStateException, IllegalArgumentException, Exception {
        if (!connected()) {
            throw new IllegalStateException("Nincs adatbazis-kapcsolat!");
        }

        if (entity == null || entity.getId() == null) {
            throw new IllegalArgumentException("A torlendo entitas null vagy nincs ID-je!");
        }

        try {
            User delEntity = em.find(User.class, entity.getUsername());

            if (delEntity.getId() == null) {
                throw new IllegalArgumentException("A t�rlend� entit�s nincs az adatb�zisban");
            }

            em.getTransaction().begin();
            em.remove(delEntity);
            em.getTransaction().commit();

        } catch (PersistenceException e) {
            throw new Exception("JPA hiba", e);
        }
    }

    public boolean validUser(User user) {
        if (!connected()) {
            throw new IllegalStateException("Nincs adatbazis-kapcsolat!");
        }
        try {
            Query query = em.createNamedQuery("User.validUser", User.class);
            query.setParameter("un", user.getUsername());
            query.setParameter("pw", user.getPassword());

            @SuppressWarnings("unchecked")
            User entity = (User) query.getSingleResult();
            if (entity != null)
                return true;
            else
                return false;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }

    public User getUserByUsername(String username) {
        if (!connected()) {
            throw new IllegalStateException("Nincs adatbazis-kapcsolat!");
        }
        try {
            Query query = em.createNamedQuery("User.getUserByUsername", User.class);
            query.setParameter("un", username);

            try {
                User entity = (User) query.getSingleResult();
                return entity;
            } catch (NoResultException ex) {
                log.error("" + ex);
                return null;
            }

        } catch (Exception e) {
            log.error("" + e);
            return null;
        }
    }

    public List<User> searchUser(String filter) {
        if (!connected()) {
            throw new IllegalStateException("Nincs adatbazis-kapcsolat!");
        }
        try {
            Query query = em.createNamedQuery("User.searchUser", User.class);
            query.setParameter("filter", filter);

            try {
                List<User> findings = (List<User>) query.getResultList();
                return findings;
            } catch (NoResultException ex) {
                log.error("" + ex);
                return null;
            }

        } catch (Exception e) {
            log.error("" + e);
            return null;
        }
    }




    public User getUserById(String userId) {
        if (!connected()) {
            throw new IllegalStateException("Nincs adatbazis-kapcsolat!");
        }
        try {
            Query query = em.createNamedQuery("User.getUserByUserId", User.class);
            query.setParameter("id", userId);

            try {
                User entity = (User) query.getSingleResult();
                return entity;
            } catch (NoResultException ex) {
                log.error("" + ex);
                return null;
            }

        } catch (Exception e) {
            log.error("" + e);
            return null;
        }
    }

    public User getUsernameByEmail(String email) {
        if (!connected()) {
            throw new IllegalStateException("Nincs adatbazis-kapcsolat!");
        }
        try {
            Query query = em.createNamedQuery("User.getUserByEmail", User.class);
            query.setParameter("un", email);

            try {
                User entity = (User) query.getSingleResult();
                return entity;
            } catch (NoResultException ex) {
                log.error("" + ex);
                return null;
            }

        } catch (Exception e) {
            log.error("" + e);
            return null;
        }
    }

    public List<User> getAllUser() {
        if (!connected()) {
            throw new IllegalStateException("Nincs adatb�zis-kapcsolat!t");
        }
        try {
            Query query = em.createNamedQuery("User.listAllUser", User.class);

            @SuppressWarnings("unchecked")
            List<User> users = new ArrayList<User>();
            users = query.getResultList();
            return users;
        } catch (Exception e) {
            log.error("" + e);
            return null;
        }
    }

}
