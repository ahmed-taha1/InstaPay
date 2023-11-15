package InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions;

public class InvalidCredentials extends Exception{
    InvalidCredentials(String message){
        super(message);
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
