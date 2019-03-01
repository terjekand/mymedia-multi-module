package mymediaMain.servlets.auth;

import lombok.extern.slf4j.Slf4j;
import mymediaMain.enums.ErrorCodes;
import mymediaMain.response.Response;
import mymediaMain.services.BCrypt;
import mymediaMain.services.Connector;
import mymediaMain.services.RegistrationService;
import org.mymedia.database.dao.UserDataBase;
import org.mymedia.database.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    private RegistrationService registrationService = new RegistrationService();

    @Override
    public void init() {

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("WEB-INF/views/index.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!request.getParameter("password-re").equals(request.getParameter("password"))) {
            response.sendRedirect("/");
        }
        User user = new User(request.getParameter("username"),
                BCrypt.hashpw(request.getParameter("password"), BCrypt.gensalt()),
                request.getParameter("email"),
                request.getParameter("fullname"));
        Response regResponse = registrationService.userRegistration(user);
        if (regResponse.getErrorCode() == ErrorCodes.OK) {
            log.info("[New user] - " + user.toString() + "\n[ErrCode:] " + regResponse.getErrorCode() + "[ErrorMsg:] " + regResponse.getErrorMessage());
        } else {
            log.info("[Bad Reg] - " + user.toString() + "\n[ErrCode:] " + regResponse.getErrorCode() + "[ErrMsg:] " + regResponse.getErrorMessage());
        }
        response.sendRedirect("/");

    }

    @Override
    public void destroy() {
//           userDB.disconnectDB();
    }
}
