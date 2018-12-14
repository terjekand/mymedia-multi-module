package mymediaMain.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mymediaMain.enums.ErrorCodes;
import mymediaMain.enums.ErrorMessages;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Response {
    private String errorMessage;
    private int errorCode;

    public Response(ErrorMessages errorMessages, ErrorCodes errorCodes){
        this.errorCode = errorCodes.getNumber();
        this.errorMessage = errorMessages.getMessage();
    }

}
