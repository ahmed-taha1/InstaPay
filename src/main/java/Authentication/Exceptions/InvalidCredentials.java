package Authentication.Exceptions;

public class InvalidCredentials extends Exception{
    InvalidCredentials(String message){
        super(message);
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
