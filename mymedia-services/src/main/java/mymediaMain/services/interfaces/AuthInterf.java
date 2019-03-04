package mymediaMain.services.interfaces;

import mymediaMain.dto.AuthDto;
import mymediaMain.response.Response;

public interface AuthInterf {

    /**
     * Used in the login screen to authenticate a user.
     * @param authDto username and password pair from the fields of login screen.
     * @return mymedia response look after at mymedia.enums.ErrorCodes and mymedia.enums.ErrorMessages
     */
    Response login(AuthDto authDto);

    /**
     * Use for logout a user by the token what its use.
     * @param token the token of the user.
     * @return mymedia response look after at mymedia.enums.ErrorCodes and mymedia.enums.ErrorMessages
     */
    Response logout(String token);
}
