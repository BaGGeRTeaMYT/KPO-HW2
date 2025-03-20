package hse_bank.MainClasses.ObjectStorages;

import hse_bank.Console.Output.Printer;
import hse_bank.MainClasses.Interfaces.SomeObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public abstract class StorageType implements Storage {
    protected Map<Integer, SomeObject> storage;
    private Printer printer;

    @Autowired
    public StorageType(Printer printer) {
        storage = new HashMap<>();
        this.printer = printer;
    }
}
