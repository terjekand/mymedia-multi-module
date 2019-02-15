package mymediaMain.servlets;

import lombok.extern.slf4j.Slf4j;
import mymediaMain.config.SessionManager;
import mymediaMain.services.Connector;
import mymediaMain.services.StatusService;
import org.mymedia.database.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/status")
public class StatusServlet extends HttpServlet {
    StatusService statusService;

    @Override
    public void init() {
        statusService = new StatusService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionManager sessionManager = SessionManager.getInstance();
        request.setAttribute("numberOfUsers", sessionManager.numberOfUsers());
        request.getRequestDispatcher("WEB-INF/views/status.jsp").forward(request, response);
    }

    public void logoutAllUser(){
        statusService.logoutAllUser();
    }

    public void registrateTemplate1(){
        statusService.registrateTemplate1();
    }

    public void registrateTemplate2(){
        statusService.registrateTemplate2();
    }

    public void registrateTemplate3(){
        statusService.registrateTemplate3();
    }

    public void deleteTemplate1(){
        statusService.deleteTemplate1();
    }

    public void deleteTemplate2(){
        statusService.deleteTemplate2();
    }
    public void deleteTemplate3(){
        statusService.deleteTemplate3();
    }

}
