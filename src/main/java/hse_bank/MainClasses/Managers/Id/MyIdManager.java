package hse_bank.MainClasses.Managers.Id;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Component
public class MyIdManager implements IdManager {
    private static final IdManager instance = new MyIdManager();

    private int nextId = 1;

    private final Set<Integer> usedIds = new HashSet<>();
    private final Map<Integer, IdOwner> idOwners = new HashMap<>();

    private MyIdManager() {}

    public static IdManager getInstance() {
        return instance;
    }

    @Override
    public int getNextId(IdOwner owner) {
        while (usedIds.contains(nextId)) {
            nextId++;
        }
        return nextId;
    }

    @Override
    public IdOwner getOwner(int id) {
        IdOwner owner = idOwners.get(id);
        if (owner == null) {
            throw new RuntimeException("Обращение по незанятому ID " + id + ".");
        }
        return owner;
    }

    @Override
    public boolean isAvailable(int id) {
        return !usedIds.contains(id);
    }

    @Override
    public void releaseId(int id) {
        usedIds.remove(id);
        idOwners.remove(id);
        if (id < nextId) {
            nextId = id;
        }
    }

    @Override
    public void reserveId(int id, IdOwner owner) {
        usedIds.add(id);
        idOwners.putIfAbsent(id, owner);
    }
}
