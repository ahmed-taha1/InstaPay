package InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions;

public class UserNotFound extends Exception{
    public UserNotFound(String message){
        super(message);
    }
    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
