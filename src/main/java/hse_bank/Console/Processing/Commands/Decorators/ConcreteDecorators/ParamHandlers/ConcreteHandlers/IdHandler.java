package hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.ConcreteHandlers;

import hse_bank.Console.Output.Printer;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.AbstractHandler;
import hse_bank.Console.Processing.Commands.CommandInfo;

import java.util.Vector;

public class IdHandler extends AbstractHandler {
    public IdHandler(Printer printer) {
        super(printer);
    }

    @Override
    public CommandInfo handle(Vector<String> tokens) {
        CommandInfo tmp = super.handle(tokens);
        Integer number = null;
        try {
            number = Integer.parseInt(tokens.elementAt(0));
        } catch (NumberFormatException e) {
            printer.print("Некорректный баланс: " + tokens.elementAt(0));
        }
        return new CommandInfo(number, tmp.name(), tmp.amount(), tmp.bAccId(), tmp.catId(), tmp.type());
    }
}
