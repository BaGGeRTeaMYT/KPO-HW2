package hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies;

import hse_bank.Console.Processing.Commands.CommandInfo;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.Handler;

import java.util.Vector;

public abstract class AbstractStrategy implements Strategy {
    protected Handler commandChain;

    @Override
    public CommandInfo process(Vector<String> tokens) {
        return commandChain.handle(tokens);
    }
}
