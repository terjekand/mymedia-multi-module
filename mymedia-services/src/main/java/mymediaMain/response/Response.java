package mymediaMain.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mymediaMain.enums.ErrorCodes;
import mymediaMain.enums.ErrorMessages;

@NoArgsConstructor
@Getter
@Setter
public class Response {
    private ErrorMessages errorMessage;
    private ErrorCodes errorCode;

    public Response(ErrorMessages errorMessages, ErrorCodes errorCodes) {
        this.errorCode = errorCodes;
        this.errorMessage = errorMessages;
    }

}
