package mymediaMain.config;


import mymediaMain.enums.ErrorCodes;
import mymediaMain.enums.ErrorMessages;
import mymediaMain.response.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


public class SessionManager {

    private Map<String, UUID> sessionMap = new HashMap<>();
    private static SessionManager instance = new SessionManager();


    private SessionManager(){

    }

    public static SessionManager getInstance(){
        return instance;
    }

    public Response addUserId(String userId){
        if(!hasPermission(userId)){
            sessionMap.put(userId, UUID.randomUUID());
            return new Response(ErrorMessages.OK, ErrorCodes.OK);
        }
        return new Response(ErrorMessages.ALREADY_AUTHORIZED_USER, ErrorCodes.ALREADY_AUTHORIZED_USER);
    }

    public boolean hasPermission(String userId){
        return sessionMap.containsKey(userId);
    }

    public UUID getUUIDofUser(String userId){
            return sessionMap.get(userId);
    }

    public Response removePermission(String userId){
        if(hasPermission(userId)){
            sessionMap.remove(userId);
            return new Response(ErrorMessages.OK, ErrorCodes.OK);
        }
        return new Response(ErrorMessages.USER_ALREADY_HAS_NO_PREMISSION, ErrorCodes.USER_ALREADY_HAS_NO_PERMISSION);
    }
    public Set<String> listAllUsersOfSession(){
        return sessionMap.keySet();
    }
}

