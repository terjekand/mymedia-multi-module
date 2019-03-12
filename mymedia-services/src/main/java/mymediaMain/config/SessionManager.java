package mymediaMain.config;


import lombok.extern.slf4j.Slf4j;
import mymediaMain.enums.ErrorCodes;
import mymediaMain.enums.ErrorMessages;
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

    public LoginResponse addUserId(String userId) {

        if (!hasPermission(userId)) {

            String token = "TK_" + UUID.randomUUID().toString();

            sessionMap.put(userId, token);
            return new LoginResponse(ErrorMessages.OK, ErrorCodes.OK, token);
        }
        return new LoginResponse(ErrorMessages.ALREADY_AUTHORIZED_USER, ErrorCodes.ALREADY_AUTHORIZED_USER, null);
    }

    public boolean hasPermission(String userId) {
        return sessionMap.containsKey(userId);
    }

    public String getTokenOfUser(String userId) {
        return sessionMap.get(userId);
    }

    private static String getUserIdByToken(Map<String, String> map, String token) {
        List<String> keys = map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), token))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        return keys.get(0);
    }

    public String getUserIdByToken(String token){
        return getUserIdByToken(sessionMap, token);
    }

    public Response removePermission(String token) {
        try {
            checkTokenFormat(token);
        } catch (MalformedTokenException ex) {

            log.error("" + ex);

            return new Response(ErrorMessages.OK, ErrorCodes.OK);
        }
        String userId = getUserIdByToken(sessionMap, token);

        if (hasPermission(userId)) {
            sessionMap.remove(userId);
            return new Response(ErrorMessages.OK, ErrorCodes.OK);
        }
        return new Response(ErrorMessages.USER_ALREADY_HAS_NO_PREMISSION, ErrorCodes.USER_ALREADY_HAS_NO_PERMISSION);
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

