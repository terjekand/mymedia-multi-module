package mymediaMain.config;


import lombok.extern.slf4j.Slf4j;
import mymediaMain.enums.ResponseUtil;
import mymediaMain.exceptions.MalformedTokenException;
import mymediaMain.response.LoginResponse;
import mymediaMain.response.Response;

import java.util.*;
import java.util.stream.Collectors;


@Slf4j
public class SessionManager {

    private Map<String, String> sessionMap = new HashMap<>();
    private static SessionManager instance = new SessionManager();


    private SessionManager() {

    }

    public static SessionManager getInstance() {
        return instance;
    }

    /**
     * User login.
     *
     * @param userId the ID of the user who's login.
     * @return status code.
     */
    public LoginResponse addUserId(String userId) {

        if (!hasPermission(userId)) {

            String token = "TK_" + UUID.randomUUID().toString();

            sessionMap.put(userId, token);
            return new LoginResponse(ResponseUtil.MSG_OK, ResponseUtil.CODE_OK, token);
        }
        return new LoginResponse(ResponseUtil.MSG_ALREADY_AUTHORIZED_USER, ResponseUtil.CODE_ALREADY_AUTHORIZED_USER, null);
    }

    /**
     * Checks if the userId can be found in the sessionMap.
     *
     * @param userId the userId what we are looking for.
     * @return true if the user has permission.
     */
    public boolean hasPermission(String userId) {
        return sessionMap.containsKey(userId);
    }

    /**
     * Returns the token of a userId.
     *
     * @param userId the key of the map.
     * @return token.
     */
    public String getTokenOfUser(String userId) {
        return sessionMap.get(userId);
    }

    /**
     * Returns the userId of the token.
     *
     * @param map usually this is the session map.
     * @param token the token of the user.
     * @return userId.
     */
    private static String getUserIdByToken(Map<String, String> map, String token) {
        List<String> keys = map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), token))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return keys.get(0);
    }

    /**
     * The public version of this function.
     *
     * @param token this usually comes from the frontend.
     * @return userId.
     */
    public String getUserIdByToken(String token){
        return getUserIdByToken(sessionMap, token);
    }

    /**
     * Used to logout a user.
     *
     * @param token the token of the user.
     * @return status response.
     */
    public Response removePermission(String token) {
        try {
            checkTokenFormat(token);
        } catch (MalformedTokenException ex) {

            log.error("" + ex);

            return new Response(ResponseUtil.MSG_OK, ResponseUtil.CODE_OK);
        }
        String userId = getUserIdByToken(sessionMap, token);

        if (hasPermission(userId)) {
            sessionMap.remove(userId);
            return new Response(ResponseUtil.MSG_OK, ResponseUtil.CODE_OK);
        }
        return new Response(ResponseUtil.MSG_USER_ALREADY_HAS_NO_PREMISSION, ResponseUtil.CODE_USER_ALREADY_HAS_NO_PERMISSION);
    }

    public List<String> listAllTokensOfSession() {
        final ArrayList<String> tokens = new ArrayList<>();
        sessionMap.values().stream().forEach(e -> tokens.add(e));
        return tokens;
    }

    public int numberOfUsers() {
        return sessionMap.size();
    }

    public void checkTokenFormat(String token) throws MalformedTokenException {
        if (!token.startsWith("TK_")) {
            throw new MalformedTokenException("Malformed token!");
        }
    }

    public void clear() {
        sessionMap = new HashMap<>();
    }
}

