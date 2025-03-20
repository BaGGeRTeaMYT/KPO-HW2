package hse_bank.Console.Processing.Commands.ConcreteCommands;

import hse_bank.Console.Output.Printer;
import hse_bank.Console.Processing.Commands.CommandType;
import hse_bank.MainClasses.CustomTypes.OperatingUnits;

public class EditCommand extends CommandType {

    public EditCommand(Printer printer) {
        super(printer);
    }

    @Override
    public Integer execute(OperatingUnits op) {
        return null;
    }
}
