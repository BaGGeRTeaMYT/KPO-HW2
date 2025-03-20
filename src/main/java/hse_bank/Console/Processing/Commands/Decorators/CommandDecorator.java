package hse_bank.Console.Processing.Commands.Decorators;

import hse_bank.Console.Output.Printer;
import hse_bank.Console.Processing.Commands.Command;
import hse_bank.Console.Processing.Commands.CommandType;
import hse_bank.MainClasses.CustomTypes.OperatingUnits;

import java.util.Vector;

public abstract class CommandDecorator implements Command {
    protected final Command decoratedCommand;
    protected final Vector<String> tokens;

    public CommandDecorator(Command decoratedCommand, Vector<String> tokens) {
        this.decoratedCommand = decoratedCommand;
        this.tokens = tokens;
    }

    public Printer printer() {
        return decoratedCommand.printer();
    }

    protected boolean elementExists(OperatingUnits op, int id) {
        return op.storage().getElementById(id) != null;
    }

    protected Integer invalidArgsCnt() {
        decoratedCommand.printer().print("Некорректное количество аргументов: " + tokens.size());
        return null;
    }
}
