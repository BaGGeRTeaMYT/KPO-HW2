package hse_bank.MainClasses.CustomTypes;

import hse_bank.Console.Output.Printer;
import hse_bank.MainClasses.Factories.Factory;
import hse_bank.MainClasses.ObjectStorages.Storage;
import org.springframework.stereotype.Component;

@Component
public record OperatingUnits(Storage storage, Factory factory) {
}
