package mymediaMain.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import mymediaMain.enums.ErrorCodes;
import mymediaMain.response.Response;
import mymediaMain.services.BCrypt;

import mymediaMain.services.RegistrationService;
import org.mymedia.database.entities.User;
import org.mymedia.database.dao.UserDataBase;

@Slf4j
@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    private RegistrationService registrationService = new RegistrationService();
    private static final UserDataBase USERDB = UserDataBase.getDataBase();

    @Override
    public void init() {
        if (!USERDB.connected()) {
            try {
                USERDB.connectDB();
                log.trace("Connect to database!");

            } catch (Exception e) {
                log.error("" + e);
            }
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/views/registration.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

            User user = new User(request.getParameter("username"),
                    BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt()),
                    request.getParameter("email"),
                    request.getParameter("fullname"));
            Response regResponse = registrationService.userRegistration(user);
            if(regResponse.getErrorCode() == ErrorCodes.OK.getNumber()){
                request.getRequestDispatcher("WEB-INF/views/login.jsp").forward(request, response);
            }
            else {
                request.getRequestDispatcher("WEB-INF/views/registration.jsp").forward(request, response);
            }

    }

	   @Override
	   public void destroy() {
//           userDB.disconnectDB();
	   }
}
