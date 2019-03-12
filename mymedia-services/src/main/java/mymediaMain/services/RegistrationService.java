package mymediaMain.services;

import database.dao.UserDataBase;
import lombok.extern.slf4j.Slf4j;
import mymediaMain.enums.ErrorCodes;
import mymediaMain.enums.ErrorMessages;
import mymediaMain.response.Response;
import database.entities.User;

import javax.ejb.Stateless;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

@Slf4j
public class RegistrationService {


    private static final UserDataBase USER_DATA_BASE = UserDataBase.getInstance();

    public RegistrationService() {
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

    private Boolean isUsedUsername(String username) {
        /**
         *return USER_DATA_BASE.getUserByUsername(username) != null;
         */
        return true;
    }

    private Boolean isUsedEmail(String email) {
        /**
         * return USER_DATA_BASE.getUsernameByEmail(email) != null;
         */
        return true;
    }

    public Response userRegistration(User user) {
        try {
             USER_DATA_BASE.save(user);
        } catch (Exception e) {
            log.error("" + e);
            return new Response(ErrorMessages.UNKNOWN_ERROR, ErrorCodes.UNKNOWN_ERROR);
        }

        return new Response(ErrorMessages.OK, ErrorCodes.OK);
    }
}
