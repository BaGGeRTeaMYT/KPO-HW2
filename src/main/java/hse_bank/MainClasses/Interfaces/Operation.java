package hse_bank.MainClasses.Interfaces;

public interface Operation extends SomeObject {
    int getId();
    String getType();
    int getBankAccountId();
    double getAmount();
    String getDate();
    Category getCategoryId();
    void setCategory(Category category);
}
