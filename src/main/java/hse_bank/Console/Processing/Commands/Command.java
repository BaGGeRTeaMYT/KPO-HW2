package hse_bank.Console.Processing.Commands;

import hse_bank.Console.Output.Printer;
import hse_bank.MainClasses.CustomTypes.OperatingUnits;

public interface Command {
    Integer execute(OperatingUnits op);
    public Printer printer();
}
