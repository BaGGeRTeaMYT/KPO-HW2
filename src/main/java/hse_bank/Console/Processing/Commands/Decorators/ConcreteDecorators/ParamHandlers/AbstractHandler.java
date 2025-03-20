package hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers;

import hse_bank.Console.Output.Printer;
import hse_bank.Console.Processing.Commands.CommandInfo;

import java.util.Vector;

public class AbstractHandler implements Handler {
    private Handler next = null;
    protected Printer printer;

    public AbstractHandler(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public CommandInfo handle(Vector<String> tokens) {
        if (next != null) {
            return next.handle(new Vector<>(tokens.subList(1, tokens.size())));
        }
        return new CommandInfo(null, null, null, null, null, null);
    }

}
