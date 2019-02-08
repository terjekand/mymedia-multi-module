package mymediaMain.servlets.actions;

import lombok.extern.slf4j.Slf4j;
import mymediaMain.services.Connector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "findings", urlPatterns = "/findings")
public class FindingsServlet extends HttpServlet {

    @Override
    public void init(){
        Connector connector = new Connector();
        connector.userConnection();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }



    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
