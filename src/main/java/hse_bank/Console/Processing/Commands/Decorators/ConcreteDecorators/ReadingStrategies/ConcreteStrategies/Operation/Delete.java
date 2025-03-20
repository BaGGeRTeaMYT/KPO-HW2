package hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.ConcreteStrategies.Operation;

import hse_bank.Console.Output.Printer;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.ConcreteHandlers.IdHandler;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.AbstractStrategy;

public class Delete extends AbstractStrategy {
    public Delete(Printer printer) {
        commandChain = new IdHandler(printer);
    }
}
