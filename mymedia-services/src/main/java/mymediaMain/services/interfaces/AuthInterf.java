package mymediaMain.services.interfaces;

import mymediaMain.dto.LoginDto;
import mymediaMain.response.LoginResponse;
import mymediaMain.response.Response;
import mymediaMain.response.UserResponse;

public interface AuthInterf {

    /**
     * Used in the login screen to authenticate a user.
     *
     * @param loginDto username and password pair from the fields of login screen.
     * @return mymedia response look after at mymedia.enums.ErrorCodes and mymedia.enums.ErrorMessages
     */
    LoginResponse login(LoginDto loginDto);

    /**
     * Use for logout a user by the token what its use.
     *
     * @param token the token of the user.
     * @return mymedia response look after at mymedia.enums.ErrorCodes and mymedia.enums.ErrorMessages
     */
    Response logout(String token);

    /**
     * Returns a user for a specific id.
     * If there is no suitable user in the database its returns null.
     *
     * @param id the id what we are looking for.
     * @return a user entity.
     */
    UserResponse getUserById(String id);

    /**
     * Returns user for a specific username.
     * If there is no suitable user in the database its returns null.
     *
     * @param username the username what we are looking for.
     * @return a user entity.
     */
    UserResponse getUserByUsername(String username);

    /**
     * Returns user for a specific email.
     * If there is no suitable user in the database its returns null.
     *
     * @param email the username what we are looking for.
     * @return a user entity.
     */
    UserResponse getUserByEmail(String email);

}
