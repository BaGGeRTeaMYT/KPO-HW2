package hse_bank.Console.Processing.Commands.ConcreteCommands;

import hse_bank.Console.Output.Printer;
import hse_bank.Console.Processing.Commands.CommandType;
import hse_bank.MainClasses.CustomTypes.OperatingUnits;

public class QuitCommand extends CommandType {

    public QuitCommand(Printer printer) {
        super(printer);
    }

    @Override
    public Integer execute(OperatingUnits op) {
        return -1;
    }
}
