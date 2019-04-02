package mymediaMain.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class Response implements Serializable {
    private String errorMessage;
    private int errorCode;

    public Response(String errorMessages, int errorCodes) {
        this.errorCode = errorCodes;
        this.errorMessage = errorMessages;
    }

}
