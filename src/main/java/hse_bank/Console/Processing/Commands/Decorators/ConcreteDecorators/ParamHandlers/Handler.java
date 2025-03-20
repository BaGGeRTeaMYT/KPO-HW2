package hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.ParamHandlers;

import hse_bank.Console.Processing.Commands.CommandInfo;

import java.util.Vector;

public interface Handler {
    void setNext(Handler next);
    CommandInfo handle(Vector<String> tokens);
}
