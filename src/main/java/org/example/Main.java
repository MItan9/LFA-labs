package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Grammar grammar = new Grammar();
        FiniteAutomaton automaton = new FiniteAutomaton(grammar);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Generate string");
            System.out.println("2. Check string");
            System.out.println("3. Classify Grammar");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    for (int i = 0; i < 5; i++) {
                        String str = grammar.generateString("S");
                        System.out.println("Generated String: " + str);
                    }
                    break;
                case 2:
                    System.out.print("Enter string to check: ");
                    String inputStr = scanner.nextLine();
                    boolean matches = automaton.checkString(inputStr);
                    System.out.println("String: " + inputStr + ", Accepted by FA: " + matches);
                    break;
                case 3:
                    System.out.println(grammar.classify());
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, 3, or 4.");
            }
        }
    }
}

