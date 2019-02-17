package mymediaMain.servlets;

import lombok.extern.slf4j.Slf4j;
import mymediaMain.config.SessionManager;
import mymediaMain.services.StatusService;

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

    SessionManager sessionManager;
    @Override
    public void init() {
        sessionManager = SessionManager.getInstance();
        statusService = new StatusService();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("numberOfUsers", sessionManager.numberOfUsers());
        request.getRequestDispatcher("WEB-INF/views/status.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("operator") == "registrateTemplate1"){
            registrateTemplate1();
        }
        else if(request.getParameter("operator").equals("registrateTemplate2")){
            registrateTemplate2();
        }
        else if(request.getParameter("operator").equals("registrateTemplate3")){
        registrateTemplate3();
        }
        else if(request.getParameter("operator").equals("deleteTemplate1")){
            deleteTemplate1();
        }
        else if(request.getParameter("operator").equals("deleteTemplate2")){
            deleteTemplate2();
        }
        else if(request.getParameter("operator").equals("deleteTemplate3")){
            deleteTemplate3();
        }
        else if(request.getParameter("operator").equals("logoutAllUser")){
            logoutAllUser();
        }
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
