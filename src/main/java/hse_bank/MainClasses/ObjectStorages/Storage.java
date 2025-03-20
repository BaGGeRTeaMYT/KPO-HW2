package hse_bank.MainClasses.ObjectStorages;

import hse_bank.MainClasses.Interfaces.SomeObject;

public interface Storage {
    void addElement(int id, SomeObject element);
    void removeById(int id);
    SomeObject getElementById(int id);
}
