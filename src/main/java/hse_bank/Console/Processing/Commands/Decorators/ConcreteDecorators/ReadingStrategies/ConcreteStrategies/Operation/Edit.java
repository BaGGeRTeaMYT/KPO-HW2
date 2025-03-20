package hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.ConcreteStrategies.Operation;

import hse_bank.Console.Output.Printer;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.ConcreteHandlers.BankIdHandler;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.ConcreteHandlers.CategoryIdHandler;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.ConcreteHandlers.IdHandler;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.Handler;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.AbstractStrategy;

public class Edit extends AbstractStrategy {
    public Edit(Printer printer) {
        Handler handler1 = new IdHandler(printer);
        Handler handler2 = new CategoryIdHandler(printer);
        handler1.setNext(handler2);
        commandChain = handler1;
    }
}
