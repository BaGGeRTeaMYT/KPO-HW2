package hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.ConcreteHandlers;

import hse_bank.Console.Output.Printer;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.AbstractHandler;
import hse_bank.Console.Processing.Commands.CommandInfo;

import java.util.Vector;

public class NameHandler extends AbstractHandler {

    public NameHandler(Printer printer) {
        super(printer);
    }

    @Override
    public CommandInfo handle(Vector<String> tokens) {
        CommandInfo tmp = super.handle(tokens);
        String name = tokens.elementAt(0);
        return new CommandInfo(tmp.id(), name, tmp.amount(), tmp.bAccId(), tmp.catId(), tmp.type());
    }
}
