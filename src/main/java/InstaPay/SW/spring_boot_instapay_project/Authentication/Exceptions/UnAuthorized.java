package InstaPay.SW.spring_boot_instapay_project.Authentication.Exceptions;

public class UnAuthorized extends Exception{
    public UnAuthorized(String message){
        super(message);
    }
    @Override
    public String getMessage() {
        return "404 UnAuthorized " + super.getMessage();
    }
}
