package mymediaMain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Token {
    /**
     * The current token of the user.
     */
    private String value;

    /**
     * When will the token expire.
     */
    private Date expirationDate;

    /**
     * Token lifetime in time mills.
     */
    private Long lifeTime;


    public Token(String value) {
        this.lifeTime = 180000L;
        this.value = value;
        refresh();
    }

    /**
     * Refresh the current token.
     *
     * @return refreshed time.
     */
    public Date refresh(){
        expirationDate = new Date(System.currentTimeMillis() + lifeTime);
        return expirationDate;
    }

    /**
     * Check if the token expiration date is before the current time.
     *
     * @return true if the token is no longer usable.
     */
    public Boolean isExpired(){
        Date currentTime = new Date();
        return currentTime.after(expirationDate);
    }

}
