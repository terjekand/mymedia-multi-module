package mymediaMain.services;

import database.dao.UserDataBase;
import database.entities.User;
import lombok.extern.slf4j.Slf4j;
import mymediaMain.config.SessionManager;
import mymediaMain.dto.UpdateProfileDto;
import mymediaMain.model.PersonalData;
import mymediaMain.model.SearchData;

import java.util.ArrayList;
import java.util.List;

/**
 * This class used to convert classes.
 */
@Slf4j
public class Converter {

    private static final UserDataBase USER_DATA_BASE = UserDataBase.getInstance();
    private static final SessionManager SESSION_MANAGER = SessionManager.getInstance();
    public static final String DIVIDER = "###";

    /**
     * Convert User to PersonalData.
     *
     * @param user entity from the database.
     * @return PersonalData object.
     */
    public static PersonalData convertUserToPersonalData(User user){
        PersonalData personalData = new PersonalData();
        personalData.setFullName(user.getFullname());
        personalData.setUsername(user.getUsername());
        personalData.setEmail(user.getEmail());
        personalData.setBio(user.getBio());
        personalData.setPhoneNumber(user.getPhoneNumber());
        return personalData;
    }

    /**
     * Convert UpdateProfileDto to User.
     * Its used when we wants to update our profile.
     *
     * @param updateProfileDto new informs about the user.
     * @return the updated User entity.
     */
    public static User convertUpdateProfileDtoToUser(UpdateProfileDto updateProfileDto){
        User user = USER_DATA_BASE.getUserById(updateProfileDto.getUserId());
        if(user == null){
            return null;
        }
        if(updateProfileDto.getBio() != null){
            user.setBio(updateProfileDto.getBio());
        }
        if(updateProfileDto.getPhoneNumber() != null){
            user.setPhoneNumber(updateProfileDto.getPhoneNumber());
        }
        if(updateProfileDto.getFullName() != null){
            user.setFullname(updateProfileDto.getFullName());
        }
        return user;
    }

    public static List<String> convertStringToIdList(String concatedIdString){
        List<String> result = new ArrayList<>();
        String tmp;

        while(concatedIdString.contains(DIVIDER)){
            tmp = concatedIdString.substring(0, concatedIdString.indexOf(DIVIDER));
            result.add(tmp);
            concatedIdString = concatedIdString.substring(concatedIdString.indexOf(DIVIDER) + DIVIDER.length());
        }
        return result;
    }

    /**
     * Convert userId list to a list witch contains Users for the ids.
     *
     * @param userIds a list with user ids.
     * @return list of users.
     */
    public static List<User> convertIdsToUsers(List<String> userIds){
        List<User> users = new ArrayList<>();
        for(String id : userIds){
            User tmp = USER_DATA_BASE.getUserById(id);
            if(tmp != null)
                users.add(tmp);
            else
                log.error("Unable to get User for this id: " + id);
        }
        return users;
    }

    /**
     * If a string contains the substring, this function will remove it.
     * If its not contains the substring, this function will add it.
     *
     * @param str the base string, where we are looking for the substring.
     * @param newStr the substring what we want to add or remove.
     * @return the new string with or without the substring.
     */
    public static String addOrRemoveIfContains(String str, String newStr){
        if(str.contains(newStr)){
            String before = str.substring(0, str.indexOf(newStr));
            String after = str.substring(str.indexOf(newStr) + newStr.length());
            str = before + after;
        }
        else{
            str += newStr;
        }
        return str;
    }

    public static SearchData convertUserToSearcData(String destUserId, User user){
        SearchData searchData = new SearchData();
        searchData.setFullName(user.getFullname());
        searchData.setUserId(user.getId());
        searchData.setUserName(user.getUsername());
        if(user.getFollowers().contains(destUserId)){
            searchData.setIsFollowed(true);
        } else {
            searchData.setIsFollowed(false);
        }

        return searchData;
    }
}
