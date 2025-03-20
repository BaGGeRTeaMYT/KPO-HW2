package hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators;

import hse_bank.Console.Processing.Commands.Command;
import hse_bank.Console.Processing.Commands.Decorators.CommandDecorator;
import hse_bank.MainClasses.CustomTypes.OperatingUnits;
import hse_bank.MainClasses.Interfaces.Category;

import java.util.Vector;

public class CategoryDecorator extends CommandDecorator {

    public CategoryDecorator(Command decoratedCommand, Vector<String> tokens) {
        super(decoratedCommand, tokens);
    }

    @Override
    public Integer execute(OperatingUnits op) {
        Category category;
        if (tokens.size() == 2) {
            category = op.factory().createCategory(tokens.elementAt(0), tokens.elementAt(1));
        } else if (tokens.size() == 3) {
            int id;
            try {
                id = Integer.parseInt(tokens.elementAt(0));
                try {
                    category = op.factory().createCategory(id, tokens.elementAt(1), tokens.elementAt(2));
                } catch (IllegalArgumentException e) {
                    decoratedCommand.printer().print(e.getMessage());
                    return null;
                }
            } catch (NumberFormatException e) {
                decoratedCommand.printer().print("Invalid ID: " + tokens.elementAt(0));
                return null;
            }
        } else {
            decoratedCommand.printer().print("Invalid number of arguments: " + tokens.size());
            return null;
        }
        op.storage().addElement(category.getId(), category);
        return 0;
    }
}
