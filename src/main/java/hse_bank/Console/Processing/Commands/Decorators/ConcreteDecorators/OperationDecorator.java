package hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators;

import hse_bank.Console.Processing.Commands.Command;
import hse_bank.Console.Processing.Commands.CommandInfo;
import hse_bank.Console.Processing.Commands.ConcreteCommands.CreateCommand;
import hse_bank.Console.Processing.Commands.ConcreteCommands.DeleteCommand;
import hse_bank.Console.Processing.Commands.Decorators.CommandDecorator;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.ConcreteStrategies.Operation.Delete;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.ConcreteStrategies.Operation.Edit;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.ConcreteStrategies.Operation.LongCreate;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.ConcreteStrategies.Operation.ShortCreate;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.Strategy;
import hse_bank.MainClasses.CustomTypes.OperatingUnits;
import hse_bank.MainClasses.Interfaces.Category;
import hse_bank.MainClasses.Interfaces.Operation;
import hse_bank.MainClasses.Interfaces.SomeObject;

import java.util.Vector;

public class OperationDecorator extends CommandDecorator {
    public OperationDecorator(Command decoratedCommand, Vector<String> tokens) {
        super(decoratedCommand, tokens);
    }

    @Override
    protected boolean checkElementType(SomeObject element) {
        return element instanceof Operation;
    }

    @Override
    public Integer execute(OperatingUnits op) {
        Strategy strategy;
        if (decoratedCommand instanceof CreateCommand) {
            if (tokens.size() == 3) {
                strategy = new ShortCreate(printer());
            } else if (tokens.size() == 4) {
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
        Integer id = info.id();
        Integer bankAccountId = info.bAccId();
        Double amount = info.amount();
        Integer categoryId = info.catId();
        Operation operation = null;
        if (decoratedCommand instanceof CreateCommand) {
            Category category = null;
            if (op.storage().getElementById(categoryId) instanceof Category) {
                category = (Category) op.storage().getElementById(categoryId);
            } else {
                printer().print("Объект с ID: " + id + " не является категорией.");
                return null;
            }
            String type = category.getType();
            if (id != null) {
                try {
                    operation = op.factory().createOperation(id, bankAccountId, amount, category);
                } catch (IllegalArgumentException e) {
                    printer().print(e.getMessage());
                    return null;
                }
            } else {
                try {
                    operation = op.factory().createOperation(bankAccountId, amount, category);
                } catch (IllegalArgumentException e) {
                    printer().print(e.getMessage());
                    return null;
                }
            }
            op.storage().addElement(operation.getId(), operation);
        } else if (decoratedCommand instanceof DeleteCommand) {
            if (elementDoNotExist(op, id)) {
                notFoundIdPrint(id);
                return null;
            }
            op.storage().removeById(id);
            op.factory().getIdManager().releaseId(id);
            decoratedCommand.printer().print("Объект с ID: " + id + " успешно удалён.");
        } else {
            if (elementDoNotExist(op, id)) {
                notFoundIdPrint(id);
                return null;
            }
            SomeObject wantedOperation = op.storage().getElementById(id);
            if (!checkElementType(wantedOperation)) {
                printer().print("Объект с ID: " + id + " не является операцией.");
                return null;
            }
            SomeObject wantedCategory = op.storage().getElementById(categoryId);
            if (!(wantedCategory instanceof Category)) {
                printer().print("Объект с ID: " + id + " не является категорией.");
                return null;
            }
            operation = (Operation) wantedOperation;
            operation.setCategory((Category) wantedCategory);
            printer().print("Изменено на: " + operation.describe());
        }
        return 0;
    }
}
