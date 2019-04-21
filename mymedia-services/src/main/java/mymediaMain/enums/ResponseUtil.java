package mymediaMain.enums;

public class ResponseUtil {

    public static int CODE_USED_USERNAME = -101;

    public static int CODE_USED_EMAIL = -102;

    public static int CODE_TOKEN_REFRESH_ERROR = -103;

    public static int CODE_USER_ALREADY_HAS_NO_PERMISSION = -104;

    public static int CODE_ALREADY_AUTHORIZED_USER = -105;

    public static int CODE_WRONG_EMAIL_FORMAT = -106;

    public static int CODE_WRONG_USERNAME = -107;

    public static int CODE_MALFORMED_TOKEN = -108;

    public static int CODE_TOKEN_DOES_NOT_EXIST = -109;

    public static int CODE_USER_NOT_FOUND = -110;

    public static int CODE_USER_UPDATE_ERROR= -111;

    public static int CODE_OK = 200;

    public static int CODE_POST_DOES_NOT_EXIST = -301;

    public static int CODE_UNAUTHORIZED = -401;

    public static int CODE_NO_DATA_BASE_CONNECTION = -500;

    public static int CODE_BAD_PARAMETERS = -501;

    public static int CODE_SAVE_ENTITY_ERROR = -502;

    public static int CODE_LIKERS_UPDATE_ERROR = -503;

    public static int CODE_SAVE_POST_FAILED = -504;

    public static int CODE_UNKNOWN_ERROR = -999;

    public static int CODE_NOT_IMPLEMENTED = -3166;

    public static String MSG_UNAUTHORIZED = "Access denied!";

    public static String MSG_USED_USERNAME = "This user name is taken!";

    public static String MSG_USED_EMAIL = "This email address is taken!";

    public static String MSG_WRONG_EMAIL_FORMAT = "This is not a valid email address!";

    public static String MSG_WRONG_USERNAME = "Wrong username!";

    public static String MSG_TOKEN_DOES_NOT_EXIST = "Token does not exist!";

    public static String MSG_USER_NOT_FOUND = "User not found!";

    public static String MSG_OK = "Successful!";

    public static String MSG_UNKNOWN_ERROR = "UNKNOWN_ERROR";

    public static String MSG_TOKEN_REFRESH_ERROR = "Token refresh error!";

    public static String MSG_ALREADY_AUTHORIZED_USER = "TThis user already has permissions!";

    public static String MSG_USER_ALREADY_HAS_NO_PREMISSION = "Already unauthorized user!";

    public static String MSG_MALFORMED_TOKEN = "Token has no prefix!";

    public static String MSG_NOT_IMPLEMENTED = "Not implemented yet!";

    public static String MSG_NO_DATA_BASE_CONNECTION = "No database connection!";

    public static String MSG_SAVE_ENTITY_ERROR = "Error while save entity!";

    public static String MSG_BAD_PARAMETERS = "Some parameters are empty or null!";

    public static String MSG_LIKERS_UPDATE_ERROR = "Some error occurred while update Liker's of a post!";

    public static String MSG_SAVE_POST_FAILED = "Failed to save post!";

    public static String MSG_POST_DOES_NOT_EXIST = "Post does not exist!";

    public static String MSG_USER_UPDATE_ERROR= "User update error!";


}
