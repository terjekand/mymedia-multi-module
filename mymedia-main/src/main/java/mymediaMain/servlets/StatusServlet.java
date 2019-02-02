package mymediaMain.servlets;

import lombok.extern.slf4j.Slf4j;
import mymediaMain.config.SessionManager;
import mymediaMain.services.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/status")
public class StatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
    SessionManager sessionManager = SessionManager.getInstance();
        request.setAttribute("numberOfUsers", sessionManager.numberOfUsers());
        request.getRequestDispatcher("WEB-INF/views/status.jsp").forward(request, response);
        }

}
