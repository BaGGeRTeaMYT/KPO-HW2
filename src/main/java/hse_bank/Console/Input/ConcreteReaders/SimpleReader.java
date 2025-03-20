package hse_bank.Console.Input.ConcreteReaders;

import hse_bank.Console.Input.Reader;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.Vector;

@Component
public class SimpleReader implements Reader {

    @Override
    public String getLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    @Override
    public Vector<String> getTokens() {
        String line = getLine();
        Vector<String> tokens = new Vector<>();
        StringBuilder currentToken = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ' ') {
                tokens.add(currentToken.toString().toLowerCase());
                currentToken.delete(0, currentToken.length());
            } else {
                currentToken.append(line.charAt(i));
            }
        }
        if (!currentToken.isEmpty()) {
            tokens.add(currentToken.toString().toLowerCase());
        }
        return tokens;
    }

    @Override
    public int getNum() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
