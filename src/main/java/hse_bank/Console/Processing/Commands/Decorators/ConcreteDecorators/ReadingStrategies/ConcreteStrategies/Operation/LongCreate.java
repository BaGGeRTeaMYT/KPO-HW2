package hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.ConcreteStrategies.Operation;

import hse_bank.Console.Output.Printer;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.AbstractHandler;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.ConcreteHandlers.*;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.Handler;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.AbstractStrategy;

public class LongCreate extends AbstractStrategy {
    public LongCreate(Printer printer) {
        Handler handler1 = new IdHandler(printer);
        Handler handler2 = new BankIdHandler(printer);
        Handler handler3 = new DoubleHandler(printer);
        Handler handler4 = new CategoryIdHandler(printer);
        handler1.setNext(handler2);
        handler2.setNext(handler3);
        handler3.setNext(handler4);
        commandChain = handler1;
    }
}
