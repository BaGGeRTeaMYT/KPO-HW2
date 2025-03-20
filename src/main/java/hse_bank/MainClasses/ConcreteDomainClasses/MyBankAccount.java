package hse_bank.MainClasses.ConcreteDomainClasses;

import hse_bank.MainClasses.Interfaces.BankAccount;

public class MyBankAccount implements BankAccount {
    private int id;
    private String name;
    private double balance;

    public MyBankAccount(int id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String describe() {
        return "{id: " + id + ", name: " + name + ", balance: " + balance + "}";
    }
}
