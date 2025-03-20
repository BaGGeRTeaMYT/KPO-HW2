package hse_bank.MainClasses.Factories.ConcreteFactories;

import hse_bank.Console.Output.Printer;
import hse_bank.MainClasses.ConcreteDomainClasses.MyBankAccount;
import hse_bank.MainClasses.ConcreteDomainClasses.MyCategory;
import hse_bank.MainClasses.ConcreteDomainClasses.MyOperation;
import hse_bank.MainClasses.Factories.Factory;
import hse_bank.MainClasses.Interfaces.BankAccount;
import hse_bank.MainClasses.Interfaces.Category;
import hse_bank.MainClasses.Interfaces.Operation;
import hse_bank.MainClasses.Managers.Id.IdManager;
import hse_bank.MainClasses.Managers.Id.IdOwner;
import hse_bank.MainClasses.Managers.Time.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyFactory implements Factory {

    private final IdManager idManager;
    private final Calendar calendar;
    private final Printer printer;

    @Autowired
    private MyFactory(IdManager idManager, Calendar calendar, Printer printer) {
        this.idManager = idManager;
        this.calendar = calendar;
        this.printer = printer;
    }

    public BankAccount createBankAccount(int id, String name, double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Баланс не должен быть отрицательным.");
        }
        if (!idManager.isAvailable(id)) {
            throw new IllegalArgumentException("ID " + id + " уже занято");
        }
        BankAccount account = new MyBankAccount(id, name, balance);
        idManager.reserveId(id, IdOwner.BANK_ACCOUNT);
        printer.print("Создан банковский аккаунт " + account.describe());
        return account;
    }

    public BankAccount createBankAccount(String name) {
        return createBankAccount(idManager.getNextId(IdOwner.BANK_ACCOUNT), name, 0.0);
    }

    private void checkData(String type, int id) {
        if (!type.equals("income") && !type.equals("expense")) {
            throw new IllegalArgumentException(type + " не является доступным типом категории.");
        }
        if (!idManager.isAvailable(id)) {
            throw new IllegalArgumentException("ID " + id + " уже занято");
        }
    }

    public Category createCategory(int id, String type, String name) {
        checkData(type, id);
        Category category = new MyCategory(id, type, name);
        idManager.reserveId(id, IdOwner.CATEGORY);
        printer.print("Создана категория " + category.describe());
        return category;
    }

    public Category createCategory(String type, String name) {
        return createCategory(idManager.getNextId(IdOwner.CATEGORY), type, name);
    }

    public Operation createOperation(int id, String type, int bankAccountId, double amount, Category categoryId) {
        checkData(type, id);
        if (idManager.getOwner(bankAccountId) != IdOwner.BANK_ACCOUNT) {
            throw new IllegalArgumentException("Объект с ID " + bankAccountId + " не является банковским аккаунтом.");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Сумма операции не может быть отрицательной.");
        }
        Operation operation = new MyOperation(id, type, bankAccountId, amount, calendar.getDate(), categoryId);
        idManager.reserveId(id, IdOwner.OPERATION);
        printer.print("Создана операция " + operation.describe());
        return operation;
    }

    public Operation createOperation(int bankAccountId, double amount, Category categoryId) {
        return createOperation(idManager.getNextId(IdOwner.OPERATION), categoryId.getType(), bankAccountId, amount, categoryId);
    }

    @Override
    public IdManager getIdManager() {
        return idManager;
    }
}
