package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Rule {
    String left;
    String right;

    Rule(String left, String right) {
        this.left = left;
        this.right = right;
    }
}

public class Grammar {
    private Random random = new Random();
    private List<Rule> rules;

    public Grammar() {
        this.rules = new ArrayList<>();
    }

    public void addRule(String left, String right) {
        this.rules.add(new Rule(left, right));
    }

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

    public String toRegex() {
        return "a*(b*(c*d*|a*(b*d)*b*)*d*)*";
    }

    public String classify() {
        // Проверка на тип 3 (регулярную) грамматику
        boolean isType3 = rules.stream().allMatch(this::isRegularRule);

        // Проверка на тип 2 (контекстно-свободную) грамматику
        boolean isType2 = !isType3 && rules.stream().allMatch(this::isContextFreeRule);

        // Проверка на тип 1 (контекстно-зависимую) грамматику
        boolean isType1 = !isType2 && rules.stream().allMatch(this::isContextSensitiveRule);

        // Если грамматика не подходит ни под один из этих типов, она считается типом 0 (неограниченной)
        boolean isType0 = !isType1 && !isType2 && !isType3;

        if (isType3) return "Type 3: Regular Grammar";
        else if (isType2) return "Type 2: Context-Free Grammar";
        else if (isType1) return "Type 1: Context-Sensitive Grammar";
        else if (isType0) return "Type 0: Unrestricted Grammar";
        else return "Unknown Grammar Type";
    }



    public boolean isRegularRule(Rule rule) {
        // Для регулярной грамматики проверяем, соответствует ли правило форматам A → aB или A → a
        return rule.right.matches("^[a-z]([A-Z]?)$");
    }

    public boolean isContextFreeRule(Rule rule) {
        // Проверяем, что слева находится ровно один нетерминал
        boolean leftSideCheck = rule.left.length() == 1 && Character.isUpperCase(rule.left.charAt(0));

        // Дополнительно проверяем, что справа есть хотя бы один символ
        boolean rightSideCheck = !rule.right.isEmpty() && rule.right.matches("^[a-zA-Z]+");

        return leftSideCheck && rightSideCheck;
    }

    public boolean isContextSensitiveRule(Rule rule) {
        // Этот метод можно оставить без изменений, так как в вашей грамматике нет явных контекстно-зависимых правил
        return rule.right.length() >= rule.left.length() && rule.right.matches(".*[A-Z].*");
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
}


