package hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators;

import hse_bank.Console.Processing.Commands.Command;
import hse_bank.Console.Processing.Commands.CommandType;
import hse_bank.Console.Processing.Commands.ConcreteCommands.CreateCommand;
import hse_bank.Console.Processing.Commands.ConcreteCommands.DeleteCommand;
import hse_bank.Console.Processing.Commands.ConcreteCommands.EditCommand;
import hse_bank.Console.Processing.Commands.Decorators.CommandDecorator;
import hse_bank.MainClasses.CustomTypes.OperatingUnits;
import hse_bank.MainClasses.Interfaces.BankAccount;
import hse_bank.MainClasses.Managers.Id.IdOwner;

import java.util.Vector;

public class BankAccountDecorator extends CommandDecorator {

    public BankAccountDecorator(Command decoratedCommand, Vector<String> tokens) {
        super(decoratedCommand, tokens);
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
        String name = getName();
        Integer id = getId();
        Double balance = getBalance();
        if (decoratedCommand instanceof CreateCommand) {
            if (tokens.size() != 1 && tokens.size() != 3) {
                return invalidArgsCnt();
            }
            BankAccount bankAccount = null;
            if (id == null && balance == null) {
                bankAccount = op.factory().createBankAccount(name);
            } else if (id != null && balance != null) {
                try {
                    bankAccount = op.factory().createBankAccount(id, tokens.elementAt(1), balance);
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
            if (tokens.size() != 1) {
                return invalidArgsCnt();
            }
            if (op.storage().getElementById(id) == null) {
                decoratedCommand.printer().print("Объект с ID: " + id + " не существует.");
                return null;
            }
            op.storage().removeById(id);
            op.factory().getIdManager().releaseId(id);
            decoratedCommand.printer().print("Объект с ID: " + id + " успешно удалён.");
        } else if (decoratedCommand instanceof EditCommand) {
            if (tokens.size() != 2) {
                return invalidArgsCnt();
            }
            if (op.storage().getElementById(id) == null) {
                decoratedCommand.printer().print("Объект с ID: " + id + " не существует.");
                return null;
            }
            if (!(op.storage().getElementById(id) instanceof BankAccount)) {
                decoratedCommand.printer().print("Объект с ID: " + id + " не является банковским аккаунтом.");
                return null;
            }
            BankAccount bankAccount = (BankAccount)op.storage().getElementById(id);
            bankAccount.setBalance(balance);
            op.storage().addElement(id, bankAccount);
            decoratedCommand.printer().print("Изменено на: " + bankAccount.describe());
        }
        return 0;
    }
}
