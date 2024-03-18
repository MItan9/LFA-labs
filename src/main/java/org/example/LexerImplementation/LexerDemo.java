package org.example.LexerImplementation;

import java.util.List;
import java.util.Scanner;

// Основной класс для демонстрации работы лексера
public class LexerDemo {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter an arithmetic expression:");
        // Считывание строки (арифметического выражения) введённой пользователем
        String input = scanner.nextLine();

        // Инициализация лексера с введённым пользователем текстом
        Lexer lexer = new Lexer(input);
        // Токенизация ввода - преобразование введённого текста в список токенов
        List<Token> tokens = lexer.tokenize();

        // Перебор полученных токенов и их вывод в консоль
        for (Token token : tokens) {
            System.out.println(token);
        }

        // Закрытие сканера для предотвращения утечки ресурсов
        scanner.close();
    }
}
