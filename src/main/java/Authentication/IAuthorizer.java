package Authentication;

import Authentication.Exceptions.UnAuthorized;

public interface IAuthorizer {
    public void isValidateAction() throws UnAuthorized;
}
