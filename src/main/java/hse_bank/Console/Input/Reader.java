package hse_bank.Console.Input;

import java.util.Vector;

public interface Reader {
    String getLine();
    Vector<String> getTokens();
    int getNum();
}
