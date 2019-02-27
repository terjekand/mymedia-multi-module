package mymediaMain.servlets.auth;

import lombok.extern.slf4j.Slf4j;
import mymediaMain.config.SessionManager;
import mymediaMain.dto.AuthDto;
import mymediaMain.enums.ErrorCodes;
import mymediaMain.response.Response;
import mymediaMain.services.AuthService;
import mymediaMain.services.BCrypt;
import mymediaMain.services.RegistrationService;
import mymediaMain.servlets.actions.NewsFeedServlet;
import org.mymedia.database.dao.UserDataBase;
import org.mymedia.database.entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicViewportUI;
import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private static final UserDataBase USERDB = UserDataBase.getDataBase();

    private AuthService authService;
    private static final SessionManager SESSION_MANAGER = SessionManager.getInstance();;

    @Override
    public void init() {
        authService = new AuthService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/views/index.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AuthDto authDto = new AuthDto();
        authDto.setUsername(request.getParameter("username"));
        authDto.setPassword(request.getParameter("password"));
        Response resp = authService.login(authDto);
        if (resp.getErrorCode() != ErrorCodes.OK) {
            request.getRequestDispatcher("WEB-INF/views/index.jsp").forward(request, response);
        }
        else {
            String userId = USERDB.getUserByUsername(authDto.getUsername()).getId();
            String token = SESSION_MANAGER.getTokenOfUser(userId);
            Cookie cookie = new Cookie("token", token);
            response.addCookie(cookie);
            response.sendRedirect("/news");
        }

    }

    @Override
    public void destroy() {

    }

}
