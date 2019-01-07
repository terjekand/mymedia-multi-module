package mymediaMain.servlets;

import lombok.extern.slf4j.Slf4j;
import mymediaMain.dto.AuthDto;
import mymediaMain.response.Response;
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
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            AuthService authService = new AuthService();
            if (request.getParameter("logout") != null) {
//                Long userId =Long.parseLong(request.getAttribute("userid")) ;
//                authService.logout(userId);
                request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
            }

        }
}
