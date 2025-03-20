package hse_bank.Console.Processing.Commands;

import hse_bank.Console.Output.Printer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public abstract class CommandType implements Command {
    private Printer printer;

    @Autowired
    public CommandType(Printer printer) {
        this.printer = printer;
    }

    public Printer printer() {
        return printer;
    }
}
