package org.example.LexerImplementation;

import java.util.List;
import java.util.Scanner;


import org.example.LexerImplementation.Nodes.ASCIITreePrinter;


// Основной класс для демонстрации работы лексера
public class LexerDemo {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter an expression to tokenize:");
            String input = scanner.nextLine();


            Lexer lexer = new Lexer(input);
            List<Token> tokens = lexer.tokenize();

            tokens.forEach(token -> System.out.println(token.getType() + ": '" + token.getText() + "'"));

            Parser parser = new Parser(lexer);
            ASTNode ast = parser.parse();

            ASCIITreePrinter printer = new ASCIITreePrinter();
            System.out.println("AST Output:");
            ast.accept(printer);

            scanner.close();
        }
}

// if ( p == 3 ) { q = 4.5 * p + d; } else { q = p - 1; }
