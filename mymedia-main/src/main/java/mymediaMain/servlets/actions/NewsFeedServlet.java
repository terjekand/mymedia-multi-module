package mymediaMain.servlets.actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.mymedia.database.dao.PostDataBase;

import org.mymedia.database.entities.User;
import org.mymedia.database.dao.UserDataBase;

@Slf4j
@WebServlet(name = "news", urlPatterns = "/news")
public class NewsFeedServlet extends HttpServlet {

    private static final UserDataBase USER_DATA_BASE = UserDataBase.getDataBase();
    private static final PostDataBase POST_DATA_BASE = PostDataBase.getDataBase();

    @Override
    public void init() {
        if(!USER_DATA_BASE.connected()){
            try {
            USER_DATA_BASE.connectDB();
            log.trace("Connected to database!");

            } catch (Exception e) {
                System.err.println(e);
            }
        }
        if(!POST_DATA_BASE.connected()){
            try {
            POST_DATA_BASE.connectDB();
            log.trace("Connected to database!");

            } catch (Exception e) {
                log.error("" + e);
            }
        }
         
    }
    

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userId = request.getParameter("userId");

        User user = USER_DATA_BASE.getUserById(userId);


        request.setAttribute("USERNAME", user.getUsername());
        request.setAttribute("FULLNAME", user.getFullname());
        request.setAttribute("EMAIL", user.getEmail());
        request.getRequestDispatcher("WEB-INF/views/news.jsp").forward(request, response);

}


}