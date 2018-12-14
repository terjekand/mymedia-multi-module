/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mymediaMain.servlets;

import java.io.IOException;
import java.util.List;

import javax.annotation.security.DeclareRoles;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.mymedia.database.dao.PostDataBase;

import org.mymedia.database.entities.User;
import org.mymedia.database.dao.UserDataBase;
import org.mymedia.database.entities.Post;

@Slf4j
@WebServlet(name = "news", urlPatterns = "/news")
//@ServletSecurity(
//        value = @HttpConstraint(
//                rolesAllowed = {
//                        "employee"
//                }),
//        httpMethodConstraints = {
//                @HttpMethodConstraint(value = "GET", rolesAllowed = {
//                        "employee"
//                })
//        })
public class LoggedInServlet extends HttpServlet {

    private static final UserDataBase USERDB = UserDataBase.getDataBase();
    private static final PostDataBase POSTDB = PostDataBase.getDataBase();

    @Override
    public void init() {
        if(!USERDB.connected()){
            try {
            USERDB.connectDB();
            log.trace("Connected to database!");

            } catch (Exception e) {
                System.err.println(e);
            }
        }
        if(!POSTDB.connected()){
            try {
            POSTDB.connectDB();
            log.trace("Connected to database!");

            } catch (Exception e) {
                log.error("" + e);
            }
        }
         
    }
    
    private List<Post> getPosts(User user){
        
        return POSTDB.getPostsOfUser(user);
    }
    
    private List<User> getUsers(){
        return USERDB.getAllUser();
    }
    

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("users", getUsers());
        request.getRequestDispatcher("WEB-INF/views/news.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public void destroy() {
        //USERDB.disconnectDB();
    }

}