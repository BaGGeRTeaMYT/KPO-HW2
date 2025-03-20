package hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.ConcreteStrategies.BankAccount;

import hse_bank.Console.Output.Printer;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.ConcreteHandlers.DoubleHandler;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.ConcreteHandlers.IdHandler;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.ConcreteHandlers.NameHandler;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.Handler;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.AbstractStrategy;

public class LongCreate extends AbstractStrategy {
    public LongCreate(Printer printer) {
        Handler handler1 = new IdHandler(printer);
        Handler handler2 = new NameHandler(printer);
        Handler handler3 = new DoubleHandler(printer);
        handler1.setNext(handler2);
        handler2.setNext(handler3);
        commandChain = handler1;
    }
}
