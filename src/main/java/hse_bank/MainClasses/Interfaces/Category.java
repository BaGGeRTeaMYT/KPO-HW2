package hse_bank.MainClasses.Interfaces;

public interface Category extends SomeObject {
    int getId();
    String getType();
    String getName();
    void setType(String type);
    void setName(String name);
}
