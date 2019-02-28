package mymediaMain.servlets.auth;

import lombok.extern.slf4j.Slf4j;
import mymediaMain.services.AuthService;
import org.mymedia.database.dao.UserDataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@WebServlet(urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    private static final UserDataBase USERDB = UserDataBase.getDataBase();

    private AuthService authService;

    @Override
    public void init() {
        authService = new AuthService();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AuthService authService = new AuthService();
        String tokenToLogout  = request.getParameter("TK");
        if (tokenToLogout!= null) {
            authService.logout(tokenToLogout);
            response.sendRedirect("/");
        }

    }
}
