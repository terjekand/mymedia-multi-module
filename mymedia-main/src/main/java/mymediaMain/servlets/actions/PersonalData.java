package mymediaMain.servlets.actions;

import lombok.extern.slf4j.Slf4j;
import org.mymedia.database.dao.UserDataBase;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet(name = "myself", urlPatterns = "/myself")
public class PersonalData extends HttpServlet {

    private static final UserDataBase USER_DATA_BASE = UserDataBase.getDataBase();

    @Override
    public void init() {
        if (!USER_DATA_BASE.connected()) {
            try {
                USER_DATA_BASE.connectDB();
            } catch (Exception ex) {
                log.error("" + ex);
            }
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


}
