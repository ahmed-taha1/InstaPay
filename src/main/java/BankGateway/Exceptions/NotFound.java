package BankGateway.Exceptions;

public class NotFound extends Exception{
    public NotFound(String message){
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
