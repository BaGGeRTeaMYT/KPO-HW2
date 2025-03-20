package hse_bank.MainClasses.Managers.Id;

public enum IdOwner {
    BANK_ACCOUNT(1, "Банковский аккаунт"),
    CATEGORY(2, "Категория"),
    OPERATION(3, "Операция");

    private final int id;
    private final String description;
    IdOwner(int id, String description) {
        this.id = id;
        this.description = description;
    }
    public int getId() {
        return id;
    }
    public String getDescription() {
        return description;
    }

    public static IdOwner getById(int id) {
        for (IdOwner owner : values()) {
            if (owner.id == id) {
                return owner;
            }
        }
        throw new IllegalArgumentException("Неизвестный ID: " + id);
    }
}
