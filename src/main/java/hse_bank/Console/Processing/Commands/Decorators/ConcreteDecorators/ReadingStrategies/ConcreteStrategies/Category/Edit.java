package hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.ConcreteStrategies.Category;

import hse_bank.Console.Output.Printer;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.ConcreteHandlers.IdHandler;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.ConcreteHandlers.NameHandler;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.ConcreteHandlers.TypeHandler;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.Handler;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies.AbstractStrategy;

public class Edit extends AbstractStrategy {
    public Edit(Printer printer) {
        Handler handler1 = new IdHandler(printer);
        Handler handler2 = new TypeHandler(printer);
        Handler handler3 = new NameHandler(printer);
        handler1.setNext(handler2);
        handler2.setNext(handler3);
        commandChain = handler1;
    }
}
