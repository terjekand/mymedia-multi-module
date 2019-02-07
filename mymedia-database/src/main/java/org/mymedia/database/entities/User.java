/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mymedia.database.entities;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Users")

@NamedQueries({
    @NamedQuery(name = "User.validUser", query = "SELECT u FROM User u WHERE u.username =:un AND u.password =:pw"),
    @NamedQuery(name = "User.getUserByUsername", query = "SELECT u FROM User u WHERE u.username =:un"),
		@NamedQuery(name = "User.getUserByUserId", query = "SELECT u FROM User u WHERE u.id =:id"),
	@NamedQuery(name = "User.getUserByEmail", query = "SELECT u FROM User u WHERE u.email =:un"),
    @NamedQuery(name = "User.listAllUser", query = "SELECT u FROM User u")
})
@Getter
@Setter
public class User implements Serializable{
	@Id
    @Column(name = "ID", nullable = false, updatable = false)
    private String id;
    
    @Column(name = "USERNAME", nullable = false, updatable = false)
    private String username;
    
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    
    @Column(name = "EMAIL", nullable = false, updatable = false)
    private String email;
    
    @Column(name = "FULLNAME", nullable = false)
    private String fullname;
    
    

	public User(String username, String password, String email, String fullname) {
		this.id = UUID.randomUUID().toString();
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
	}
	public User() {
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", fullname=" + fullname + "]";
	}


    
    
    
}
