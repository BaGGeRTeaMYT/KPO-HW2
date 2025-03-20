package hse_bank.MainClasses.Factories;

import hse_bank.MainClasses.Interfaces.BankAccount;
import hse_bank.MainClasses.Interfaces.Category;
import hse_bank.MainClasses.Interfaces.Operation;
import hse_bank.MainClasses.Managers.Id.IdManager;

public interface Factory {
    BankAccount createBankAccount(int id, String name, double balance);
    BankAccount createBankAccount(String name);
    Category createCategory(int id, String type, String name);
    Category createCategory(String type, String name);
    Operation createOperation(int id, int bankAccountId, double amount, Category categoryId);
    Operation createOperation(int bankAccountId, double amount, Category categoryId);
    IdManager getIdManager();
}
