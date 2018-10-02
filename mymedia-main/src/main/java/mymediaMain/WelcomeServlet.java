package mymediaMain;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import mymediaMain.services.BCrypt;

import org.mymedia.database.entities.User;
import org.mymedia.database.dao.UserDataBase;

@Slf4j
@WebServlet(urlPatterns = "/login")
public class WelcomeServlet extends HttpServlet {

    private static final UserDataBase USERDB = UserDataBase.getDataBase();

    @Override
    public void init() {

        try {
            USERDB.connectDB();
            log.trace("Connected to database!");

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("password"));
        User user = USERDB.getUser(request.getParameter("username"));
        if (user == null) {
            request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
        }
        if (user != null && BCrypt.checkpw(request.getParameter("password"), BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt()))) {
            log.trace("USER INFO");
            log.trace(user.getUsername());
            log.trace(user.getPassword());
            request.setAttribute("username", request.getParameter("username"));
            request.setAttribute("password", request.getParameter("password"));
            request.getRequestDispatcher("WEB-INF/views/welcome.jsp").forward(request, response);
        }

    }

    @Override
    public void destroy() {
        USERDB.disconnectDB();
    }

}
