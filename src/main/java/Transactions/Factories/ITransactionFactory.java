package Transactions.Factories;

import Exceptions.CustomException;
import Transactions.Entities.ITransaction;

import java.util.Map;

interface ITransactionFactory {
    public ITransaction createTransaction(Map<String, Object> attributes) throws CustomException;
}
