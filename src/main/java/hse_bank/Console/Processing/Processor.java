package hse_bank.Console.Processing;

import hse_bank.Console.Input.Reader;
import hse_bank.Console.Output.Printer;
import hse_bank.Console.Processing.Commands.Command;
import org.springframework.stereotype.Component;

public interface Processor {
    Command readCommand();
}
