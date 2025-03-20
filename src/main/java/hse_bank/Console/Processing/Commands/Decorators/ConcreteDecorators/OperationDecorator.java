package hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators;

import hse_bank.Console.Processing.Commands.Command;
import hse_bank.Console.Processing.Commands.Decorators.CommandDecorator;
import hse_bank.MainClasses.CustomTypes.OperatingUnits;

import java.util.Vector;

public class OperationDecorator extends CommandDecorator {
    public OperationDecorator(Command decoratedCommand, Vector<String> tokens) {
        super(decoratedCommand, tokens);
    }

    @Override
    public Integer execute(OperatingUnits op) {
        return null;
    }
}
