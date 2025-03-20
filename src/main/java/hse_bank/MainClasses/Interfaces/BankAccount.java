package hse_bank.MainClasses.Interfaces;

public interface BankAccount extends SomeObject {
    int getId();
    String getName();
    double getBalance();
    void setBalance(double balance);
}
