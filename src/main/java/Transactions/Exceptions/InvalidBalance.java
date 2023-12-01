package Transactions.Exceptions;

public class InvalidBalance extends Exception{
    public InvalidBalance(String message){
        super(message);
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
