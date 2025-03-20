package hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.ConcreteStrategies.Operation;

import hse_bank.Console.Output.Printer;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.ConcreteHandlers.BankIdHandler;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.ConcreteHandlers.CategoryIdHandler;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.ConcreteHandlers.DoubleHandler;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.ConcreteHandlers.IdHandler;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.Handler;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.AbstractStrategy;

public class ShortCreate extends AbstractStrategy {
    public ShortCreate(Printer printer) {
        Handler handler1 = new BankIdHandler(printer);
        Handler handler2 = new DoubleHandler(printer);
        Handler handler3 = new CategoryIdHandler(printer);
        handler1.setNext(handler2);
        handler2.setNext(handler3);
        commandChain = handler1;
    }
}
