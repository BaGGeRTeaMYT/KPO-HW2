package hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators;

import hse_bank.Console.Processing.Commands.Command;
import hse_bank.Console.Processing.Commands.CommandInfo;
import hse_bank.Console.Processing.Commands.ConcreteCommands.CreateCommand;
import hse_bank.Console.Processing.Commands.ConcreteCommands.DeleteCommand;
import hse_bank.Console.Processing.Commands.ConcreteCommands.EditCommand;
import hse_bank.Console.Processing.Commands.Decorators.CommandDecorator;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.ConcreteStrategies.BankAccount.Delete;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.ConcreteStrategies.BankAccount.Edit;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.ConcreteStrategies.BankAccount.LongCreate;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.ConcreteStrategies.BankAccount.ShortCreate;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.Strategy;
import hse_bank.MainClasses.CustomTypes.OperatingUnits;
import hse_bank.MainClasses.Interfaces.BankAccount;
import hse_bank.MainClasses.Interfaces.SomeObject;

import java.util.Vector;

public class BankAccountDecorator extends CommandDecorator {

    public BankAccountDecorator(Command decoratedCommand, Vector<String> tokens) {
        super(decoratedCommand, tokens);
    }

    @Override
    protected boolean checkElementType(SomeObject element) {
        return element instanceof BankAccount;
    }

    private Integer getId() {
        if (decoratedCommand instanceof DeleteCommand || decoratedCommand instanceof EditCommand) {
            if (tokens.isEmpty()) {
                return null;
            }
            return Integer.parseInt(tokens.elementAt(0));
        }
        if (tokens.size() != 3) {
            return null;
        }
        Integer id = null;
        try {
            id = Integer.parseInt(tokens.elementAt(0));
        } catch (NumberFormatException e) {
            decoratedCommand.printer().print("Некорректный ID: " + tokens.elementAt(0));
            return null;
        }
        return id;
    }

    private Double getBalance() {
        if (decoratedCommand instanceof EditCommand) {
            if (tokens.size() < 2) {
                return null;
            }
            return Double.parseDouble(tokens.elementAt(1));
        }
        if (tokens.size() != 3) {
            return null;
        }
        Double balance = null;
        try {
            balance = Double.parseDouble(tokens.elementAt(2));
        } catch (NumberFormatException e) {
            decoratedCommand.printer().print("Некорректный баланс: " + tokens.elementAt(2));
            return null;
        }
        return balance;
    }

    private String getName() {
        if (tokens.size() == 3) {
            return tokens.elementAt(1);
        }
        return tokens.elementAt(0);
    }

    @Override
    public Integer execute(OperatingUnits op) {
        Strategy strategy;
        if (decoratedCommand instanceof CreateCommand) {
            if (tokens.size() == 1) {
                strategy = new ShortCreate(printer());
            } else if (tokens.size() == 3) {
                strategy = new LongCreate(printer());
            } else {
                return invalidArgsCnt();
            }
        } else if (decoratedCommand instanceof DeleteCommand) {
            if (tokens.size() == 1) {
                strategy = new Delete(printer());
            } else {
                return invalidArgsCnt();
            }
        } else {
            if (tokens.size() == 2) {
                strategy = new Edit(printer());
            } else {
                return invalidArgsCnt();
            }
        }
        CommandInfo info = strategy.process(tokens);
        String name = info.name();
        Integer id = info.id();
        Double balance = info.amount();
        if (decoratedCommand instanceof CreateCommand) {
            BankAccount bankAccount = null;
            if (id == null && balance == null) {
                bankAccount = op.factory().createBankAccount(name);
            } else if (id != null && balance != null) {
                try {
                    bankAccount = op.factory().createBankAccount(id, name, balance);
                } catch (IllegalArgumentException e) {
                    decoratedCommand.printer().print(e.getMessage());
                    return null;
                }
            } else {
                decoratedCommand.printer().print("Что-то пошло не так.");
                return null;
            }
            op.storage().addElement(bankAccount.getId(), bankAccount);
        } else if (decoratedCommand instanceof DeleteCommand) {
            if (elementDoNotExist(op, id)) {
                notFoundIdPrint(id);
                return null;
            }
            op.storage().removeById(id);
            op.factory().getIdManager().releaseId(id);
            decoratedCommand.printer().print("Объект с ID: " + id + " успешно удалён.");
        } else if (decoratedCommand instanceof EditCommand) {
            if (elementDoNotExist(op, id)) {
                notFoundIdPrint(id);
                return null;
            }
            if (!checkElementType(op.storage().getElementById(id))) {
                decoratedCommand.printer().print("Объект с ID: " + id + " не является банковским аккаунтом.");
                return null;
            }
            ((BankAccount)op.storage().getElementById(id)).setBalance(balance);
            decoratedCommand.printer().print("Изменено на: " + ((BankAccount)op.storage().getElementById(id)).describe());
        }
        return 0;
    }
}
