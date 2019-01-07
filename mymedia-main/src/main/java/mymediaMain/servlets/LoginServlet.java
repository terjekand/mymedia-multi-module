package mymediaMain.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import mymediaMain.config.SessionManager;
import mymediaMain.dto.AuthDto;
import mymediaMain.response.Response;
import mymediaMain.services.AuthService;
import org.mymedia.database.dao.UserDataBase;

@Slf4j
@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private static final UserDataBase USERDB = UserDataBase.getDataBase();

    private AuthService authService;
    @Override
    public void init() {
        authService = new AuthService();

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
        SessionManager sessionManager = SessionManager.getInstance();
        System.out.println(sessionManager.listAllUsersOfSession());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AuthDto authDto = new AuthDto();
        authDto.setUsername(request.getParameter("username"));
        authDto.setPassword(request.getParameter("password"));
        Response resp = authService.login(authDto);
        if (resp.getErrorCode() != 200) {
            request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
        }
        if (resp.getErrorCode() == 200){
            request.setAttribute("userid", USERDB.getUserByUsername(authDto.getUsername()).getId());
            request.getRequestDispatcher("WEB-INF/views/news.jsp").forward(request, response);
        }

    }

    @Override
    public void destroy(){

    }

}
