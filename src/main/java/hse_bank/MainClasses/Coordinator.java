package hse_bank.MainClasses;

import hse_bank.Console.Output.Printer;
import hse_bank.Console.Processing.Commands.Command;
import hse_bank.Console.Processing.Processor;
import hse_bank.MainClasses.CustomTypes.OperatingUnits;
import hse_bank.MainClasses.Factories.Factory;
import hse_bank.MainClasses.ObjectStorages.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Coordinator {
    private final OperatingUnits unit;
    private final Processor processor;
    private final Printer printer;

    @Autowired
    public Coordinator(Storage storage, Factory factory, Processor processor, Printer printer) {
        this.unit = new OperatingUnits(storage, factory);
        this.processor = processor;
        this.printer = printer;
    }

    public void coordinate() {
        Integer result = 0;
        while (result >= 0) {
            Command cmd = processor.readCommand();
            result = cmd.execute(unit);
            if (result == null) {
                printer.print("Failed to execute command");
                result = 0;
            }
        }
    }
}
