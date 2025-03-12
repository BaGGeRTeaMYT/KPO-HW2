package hse_bank.MainClasses;

public class Category {
    private final int id;
    private final String type;
    private final String name;
    public Category(int id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }
    public int getId() {
        return id;
    }
    public String getType() {
        return type;
    }
    public String getName() {
        return name;
    }
}
