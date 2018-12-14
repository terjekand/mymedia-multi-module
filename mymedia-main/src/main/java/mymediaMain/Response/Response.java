package mymediaMain.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mymediaMain.Enums.ErrorCodes;
import mymediaMain.Enums.ErrorMessages;

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
