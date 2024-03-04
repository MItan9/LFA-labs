package org.example;


import org.example.FA.State;
import org.example.FA.impl.FAConverter;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        // Define the states
        State q0 = new State("q0");
        State q1 = new State("q1");
        State q2 = new State("q2");

        // Define transitions
        q0.addTransition('a', q0);
        q0.addTransition('b', q1);
        q1.addTransition('c', q1);
        q1.addTransition('c', q2);
        q1.addTransition('a', q1);
        q2.addTransition('a', q0);

        // List of states for easier iteration
        List<State> states = Arrays.asList(q0, q1, q2);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Convert FA to Regular Grammar");
            System.out.println("2. Determine if the FA is DFA or NDFA");
            System.out.println("3. Convert NDFA to DFA");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    FAConverter.convertToRegularGrammar(states);
                    break;
                case 2:
                    FAConverter.determineFA(states);
                    break;
                case 3:
                    FAConverter.convertNDFAtoDFA(states);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, please enter a number between 1 and 4.");
            }
        }
    }
}

