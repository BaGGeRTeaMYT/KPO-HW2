package hse_bank.MainClasses;

public class BankAccount {
    private final int id;
    private final String name;
    private final int balance;
    public BankAccount(int id, String name, int balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getBalance() {
        return balance;
    }
}
