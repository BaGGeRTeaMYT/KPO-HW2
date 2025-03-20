package hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.ConcreteHandlers;

import hse_bank.Console.Output.Printer;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.AbstractHandler;
import hse_bank.Console.Processing.Commands.CommandInfo;

import java.util.Vector;

public class CategoryIdHandler extends AbstractHandler {
    public CategoryIdHandler(Printer printer) {
        super(printer);
    }

    @Override
    public CommandInfo handle(Vector<String> tokens) {
        CommandInfo tmp = super.handle(tokens);
        Integer number = null;
        try {
            number = Integer.parseInt(tokens.elementAt(0));
        } catch (NumberFormatException e) {
            printer.print("Некорректный ID: " + tokens.elementAt(0));
        }
        return new CommandInfo(tmp.id(), tmp.name(), tmp.amount(), tmp.bAccId(), number, tmp.type());
    }
}
