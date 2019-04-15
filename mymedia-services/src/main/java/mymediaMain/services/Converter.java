package mymediaMain.services;

import database.dao.UserDataBase;
import database.entities.User;
import mymediaMain.dto.UpdateProfileDto;
import mymediaMain.model.PersonalData;

/**
 * This class used to convert classes.
 */
public class Converter {

    private static final UserDataBase USER_DATA_BASE = UserDataBase.getInstance();

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
        user.setBio(updateProfileDto.getBio());
        user.setPhoneNumber(updateProfileDto.getPhoneNumber());
        return user;
    }
}
