package hse_bank.Console.Processing.ConcreteProcessors;

import hse_bank.Console.Input.Reader;
import hse_bank.Console.Output.Printer;
import hse_bank.Console.Processing.Commands.*;
import hse_bank.Console.Processing.Commands.ConcreteCommands.*;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.BankAccountDecorator;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.CategoryDecorator;
import hse_bank.Console.Processing.Commands.Decorators.ConcreteDecorators.OperationDecorator;
import hse_bank.Console.Processing.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Vector;

@Component
public class MyProcessor implements Processor {

    private final Printer printer;
    private final Reader reader;

    @Autowired
    private MyProcessor(Printer printer, Reader reader) {
        this.printer = printer;
        this.reader = reader;
    }

    @Override
    public Command readCommand() {
        Vector<String> cmd = reader.getTokens();
        Command to_execute;
        boolean needSecondArg = true;
        switch (cmd.elementAt(0)) {
            case "quit": {
                to_execute = new QuitCommand(printer);
                needSecondArg = false;
                break;
            }
            case "create": {
                to_execute = new CreateCommand(printer);
                break;
            }
            case "edit": {
                to_execute = new EditCommand(printer);
                break;
            }
            case "delete": {
                to_execute = new DeleteCommand(printer);
                break;
            }
            default: {
                cmd.insertElementAt("Invalid command:", 0);
                printer.print(cmd);
                to_execute = new ReadCommand(printer);
                needSecondArg = false;
            }
        }
        if (needSecondArg) {
            if (cmd.size() < 2) {
                cmd.insertElementAt("Command: {", 0);
                cmd.insertElementAt("} needs extra arguments!", cmd.size());
                printer.print(cmd);
                to_execute = new ReadCommand(printer);
            } else {
                switch (cmd.elementAt(1)) {
                    case "bank_account": {
                        to_execute = new BankAccountDecorator(to_execute, new Vector<>(cmd.subList(2, cmd.size())));
                        break;
                    }
                    case "category": {
                        to_execute = new CategoryDecorator(to_execute, new Vector<>(cmd.subList(2, cmd.size())));
                        break;
                    }
                    case "operation": {
                        to_execute = new OperationDecorator(to_execute, new Vector<>(cmd.subList(2, cmd.size())));
                        break;
                    }
                    default: {
                        cmd.insertElementAt("Invalid command:", 0);
                        printer.print(cmd);
                        to_execute = new ReadCommand(printer);
                    }
                }
            }
        }
        return to_execute;
    }
}
