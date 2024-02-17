package org.example;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grammar {
    private Random random = new Random();

    public String generateString(String current) {
        if (current.length() > 20) {
            return current;
        }

        int lastNonTerminalIndex = findLastNonTerminal(current);
        if (lastNonTerminalIndex == -1) {
            return current;
        }

        char nonTerminal = current.charAt(lastNonTerminalIndex);
        String replacement = chooseReplacement(nonTerminal);
        String newString = current.substring(0, lastNonTerminalIndex) + replacement + current.substring(lastNonTerminalIndex + 1);

        return generateString(newString);
    }

    private int findLastNonTerminal(String str) {
        for (int i = str.length() - 1; i >= 0; i--) {
            if (str.charAt(i) == 'S' || str.charAt(i) == 'B' || str.charAt(i) == 'D') {
                return i;
            }
        }
        return -1;
    }

    private String chooseReplacement(char nonTerminal) {
        switch (nonTerminal) {
            case 'S':
                return random.nextBoolean() ? "aS" : "bB";
            case 'B':
                int choice = random.nextInt(3);
                if (choice == 0) return "cB";
                else if (choice == 1) return "d";
                else return "aD";
            case 'D':
                return random.nextBoolean() ? "aB" : "b";
            default:
                return "";
        }
    }

    // Метод для получения регулярного выражения из грамматики
    public String toRegex() {
        return "a*(b(c*d|a(b*d)*b)*d)*";
    }
}


class FiniteAutomaton {
    private Pattern pattern;

    public FiniteAutomaton(Grammar grammar) {
        String regex = grammar.toRegex();
        pattern = Pattern.compile(regex);
    }

    public boolean checkString(String str) {
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }

    public static void main(String[] args) {
        Grammar grammar = new Grammar();
        FiniteAutomaton automaton = new FiniteAutomaton(grammar);


        for (int i = 0; i < 5; i++) {
            String str = grammar.generateString("S");
            System.out.println("String: " + str + ", Accepted by FA: " + automaton.checkString(str));
        }
    }
}
