package hse_bank.MainClasses.ConcreteDomainClasses;

import hse_bank.MainClasses.Interfaces.Category;

public class MyCategory implements Category {
    private int id;
    private String type;
    private String name;

    public MyCategory(int id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String describe() {
        return "{id: " + id + ", type: " + type + ", name: " + name + "}";
    }
}
