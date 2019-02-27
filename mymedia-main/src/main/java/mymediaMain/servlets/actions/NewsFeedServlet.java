package mymediaMain.servlets.actions;

import lombok.extern.slf4j.Slf4j;
import mymediaMain.config.SessionManager;
import mymediaMain.services.Connector;
import org.mymedia.database.dao.PostDataBase;
import org.mymedia.database.dao.UserDataBase;
import org.mymedia.database.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "news", urlPatterns = "/news")
public class NewsFeedServlet extends HttpServlet {

    private static final UserDataBase USER_DATA_BASE = UserDataBase.getDataBase();
    private static final PostDataBase POST_DATA_BASE = PostDataBase.getDataBase();
    private static final SessionManager SESSION_MANAGER = SessionManager.getInstance();

    @Override
    public void init() {
      Connector connector = new Connector();
      connector.fullConnection();

    }


    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String token = null;
        final Cookie[] cookies = request.getCookies();
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("token")){
                token = cookie.getValue();
                break;
            }
        }
        if(token != null){
            String userId = SESSION_MANAGER.getUserIdByToken(token);
            if(userId == null || userId.equals("")){
                log.error("No user found for the given token... [REDIRECT] To Login");
                response.sendRedirect("/");
            }
            User user = USER_DATA_BASE.getUserById(userId);
            request.setAttribute("USERNAME", user.getUsername());
            request.setAttribute("FULLNAME", user.getFullname());
            request.setAttribute("EMAIL", user.getEmail());

            request.getRequestDispatcher("WEB-INF/views/news.jsp").forward(request, response);
        }
        else{
            response.sendRedirect("/");
        }


    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        String userId = request.getParameter("userId");
//
//        User user = USER_DATA_BASE.getUserById(userId);
//
//
//        request.setAttribute("USERNAME", user.getUsername());
//        request.setAttribute("FULLNAME", user.getFullname());
//        request.setAttribute("EMAIL", user.getEmail());
        request.getRequestDispatcher("WEB-INF/views/news.jsp").forward(request, response);

    }


}