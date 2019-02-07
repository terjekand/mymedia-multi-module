package mymediaMain.services;

import lombok.extern.slf4j.Slf4j;
import mymediaMain.enums.ErrorCodes;
import mymediaMain.enums.ErrorMessages;
import mymediaMain.response.Response;
import org.mymedia.database.dao.UserDataBase;
import org.mymedia.database.entities.User;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Slf4j
public class RegistrationService {


    private static final UserDataBase USERDB = UserDataBase.getDataBase();

    public RegistrationService() {
        if (!USERDB.connected()) {
            try {
                USERDB.connectDB();
                log.trace("Connect to database!");

            } catch (Exception e) {
                log.error("" + e);
            }
        }
    }

    private static boolean isValidEmail(String email) {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            log.error("" + ex);
            return false;
        }
        return true;
    }

    private boolean isUsedUsername(String username) {

        return USERDB.getUserByUsername(username) != null;
    }

    private boolean isUsedEmail(String email) {

        return USERDB.getUsernameByEmail(email) != null;
    }

    public Response userRegistration(User user) {

        if (isUsedUsername(user.getUsername())) {
            return new Response(ErrorMessages.USED_USERNAME, ErrorCodes.USED_USERNAME);
        } else if (isValidEmail(user.getEmail())) {
            return new Response(ErrorMessages.WRONG_EMAIL_FORMAT, ErrorCodes.WRONG_EMAIL_FORMAT);
        } else if (isUsedEmail(user.getEmail())) {
            return new Response(ErrorMessages.USED_EMAIL, ErrorCodes.USED_EMAIL);
        }

        try {
            USERDB.save(user);
        } catch (Exception e) {
            log.error("" + e);
            return new Response(ErrorMessages.UNKNOWN_ERROR, ErrorCodes.UNKNOWN_ERROR);
        }

        return new Response(ErrorMessages.OK, ErrorCodes.OK);
    }
}
