package org.example.LexerImplementation;

import java.util.List;
import java.util.Scanner;

// Main class to demonstrate the lexer
public class LexerDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an arithmetic expression:");
        String input = scanner.nextLine(); // Read user input from console

        Lexer lexer = new Lexer(input); // Initialize lexer with the input
        List<Token> tokens = lexer.tokenize(); // Tokenize the input

        // Iterate over tokens and print them
        for (Token token : tokens) {
            System.out.println(token);
        }

        scanner.close(); // Close the scanner to prevent resource leak
    }
}
