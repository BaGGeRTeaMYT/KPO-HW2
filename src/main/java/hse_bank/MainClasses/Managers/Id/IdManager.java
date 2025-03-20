package hse_bank.MainClasses.Managers.Id;

public interface IdManager {
    int getNextId(IdOwner owner);

    IdOwner getOwner(int id);

    boolean isAvailable(int id);

    void releaseId(int id);

    void reserveId(int id, IdOwner owner);
}
