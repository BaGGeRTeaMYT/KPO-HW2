package hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators;

import hse_bank.Console.Processing.Commands.Command;
import hse_bank.Console.Processing.Commands.CommandInfo;
import hse_bank.Console.Processing.Commands.ConcreteCommands.CreateCommand;
import hse_bank.Console.Processing.Commands.ConcreteCommands.DeleteCommand;
import hse_bank.Console.Processing.Commands.ConcreteCommands.EditCommand;
import hse_bank.Console.Processing.Commands.Decorators.CommandDecorator;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.ConcreteStrategies.Category.Delete;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.ConcreteStrategies.Category.Edit;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.ConcreteStrategies.Category.LongCreate;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.ConcreteStrategies.Category.ShortCreate;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.Strategy;
import hse_bank.MainClasses.CustomTypes.OperatingUnits;
import hse_bank.MainClasses.Interfaces.BankAccount;
import hse_bank.MainClasses.Interfaces.Category;

import java.util.Vector;

public class CategoryDecorator extends CommandDecorator {

    public CategoryDecorator(Command decoratedCommand, Vector<String> tokens) {
        super(decoratedCommand, tokens);
    }

    @Override
    public Integer execute(OperatingUnits op) {
        Strategy strategy;
        if (decoratedCommand instanceof CreateCommand) {
            if (tokens.size() == 2) {
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
            if (tokens.size() == 3) {
                strategy = new Edit(printer());
            } else {
                return invalidArgsCnt();
            }
        }
        CommandInfo info = strategy.process(tokens);
        Integer id = info.id();
        String type = info.type();
        String name = info.name();
        Category category = null;

        if (decoratedCommand instanceof CreateCommand) {
            if (id != null) {
                try {
                    category = op.factory().createCategory(id, type, name);
                } catch (IllegalArgumentException e) {
                    printer().print(e.getMessage());
                    return null;
                }
            } else {
                try {
                    category = op.factory().createCategory(type, name);
                } catch (IllegalArgumentException e) {
                    printer().print(e.getMessage());
                    return null;
                }
            }
            op.storage().addElement(category.getId(), category);
        } else if (decoratedCommand instanceof DeleteCommand) {
            if (!elementExists(op, id)) {
                printer().print("Объект с ID: " + id + " не существует.");
                return null;
            }
            op.storage().removeById(id);
            op.factory().getIdManager().releaseId(id);
            decoratedCommand.printer().print("Объект с ID: " + id + " успешно удалён.");
        } else if (decoratedCommand instanceof EditCommand) {
            if (!elementExists(op, id)) {
                decoratedCommand.printer().print("Объект с ID: " + id + " не существует.");
                return null;
            }
            if (!(op.storage().getElementById(id) instanceof Category)) {
                decoratedCommand.printer().print("Объект с ID: " + id + " не является категорией.");
                return null;
            }
            category = (Category) op.storage().getElementById(id);
            category.setType(type);
            category.setName(name);
            decoratedCommand.printer().print("Изменено на: " + category.describe());
        }


        return 0;
    }
}
