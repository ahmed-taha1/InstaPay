package Authentication.Exceptions;

public class UnAuthorized extends Exception{
    public UnAuthorized(String message){
        super(message);
    }
    @Override
    public String getMessage() {
        return "404 UnAuthorized " + super.getMessage();
    }
}
