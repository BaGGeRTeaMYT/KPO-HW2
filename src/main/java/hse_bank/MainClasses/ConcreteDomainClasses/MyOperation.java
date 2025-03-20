package hse_bank.MainClasses.ConcreteDomainClasses;

import hse_bank.MainClasses.Interfaces.Category;
import hse_bank.MainClasses.Interfaces.Operation;

public class MyOperation implements Operation {
    private final int id;
    private final String type; // "income" или "expense"
    private final int bankAccountId;
    private final double amount;
    private final String date;
    private final Category categoryId;

    public MyOperation(int id, String type, int bankAccountId, double amount, String date, Category categoryId) {
        this.id = id;
        this.type = type;
        this.bankAccountId = bankAccountId;
        this.amount = amount;
        this.date = date;
        this.categoryId = categoryId;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public int getBankAccountId() {
        return bankAccountId;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public Category getCategoryId() {
        return categoryId;
    }

    @Override
    public String describe() {
        return "{id: " + id + ", type: " + type + ", bankAccountId: " + bankAccountId + ", amount: " + amount + " date: " + date + ", category: " + categoryId.describe() + "}";
    }
}
