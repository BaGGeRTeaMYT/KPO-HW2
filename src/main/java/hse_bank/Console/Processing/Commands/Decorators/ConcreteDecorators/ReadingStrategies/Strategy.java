package hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ReadingStrategies;

import hse_bank.Console.Processing.Commands.CommandInfo;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers.Handler;

import java.util.Vector;

public interface Strategy {
    CommandInfo process(Vector<String> tokens);
}
