package mymediaMain.exceptions;

public class MalformedTokenException extends Exception {

    public MalformedTokenException(){
        super();
    }

    public MalformedTokenException(String message) {
        super(message);
    }

    public MalformedTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public MalformedTokenException(Throwable cause) {
        super(cause);
    }

}
