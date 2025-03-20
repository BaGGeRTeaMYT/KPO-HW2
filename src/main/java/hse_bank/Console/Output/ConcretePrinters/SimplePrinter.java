package hse_bank.Console.Output.ConcretePrinters;

import hse_bank.Console.Output.Printer;
import org.springframework.stereotype.Component;

import java.util.Vector;

@Component
public class SimplePrinter implements Printer {

    @Override
    public void print(String toPrint) {
        System.out.println(toPrint);
    }

    @Override
    public void print(Vector<String> toPrint) {
        System.out.println(String.join(" ", toPrint));
    }
}
