package Gateways;

import Exceptions.CustomException;

public interface IPaymentGateway {
    public void depositMoney(double amount) throws CustomException;
    public void withdrawMoney(double amount) throws CustomException;
    public double getBalance() throws CustomException;
}
