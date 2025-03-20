package hse_bank.MainClasses.ObjectStorages;

import hse_bank.Console.Output.Printer;
import hse_bank.MainClasses.Interfaces.SomeObject;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MyStorage extends StorageType {

    public MyStorage(Printer printer) {
        super(printer);
    }

    @Override
    public void addElement(int id, SomeObject element) {
        storage.put(id, element);
    }

    @Override
    public void removeById(int id) {
        storage.remove(id);
    }

    @Override
    public SomeObject getElementById(int id) {
        return storage.get(id);
    }
}
