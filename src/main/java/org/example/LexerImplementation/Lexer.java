package org.example.LexerImplementation;

import java.util.ArrayList;
import java.util.List;

// Класс Lexer для лексического анализа входного текста.
public class Lexer {
    private final String input; // Входная строка для анализа
    private int position = 0; // Текущая позиция во входной строке
    private char currentChar; // Текущий символ на текущей позиции

    // Конструктор класса Lexer. Принимает входную строку для анализа.
    public Lexer(String input) {
        this.input = input + "\0"; // Добавление символа конца файла (EOF) к входной строке
        currentChar = this.input.charAt(position); // Получение текущего символа
    }

    // Метод для продвижения позиции на один символ вперёд.
    private void advance() {
        position++;
        if (position > input.length() - 1) {
            currentChar = '\0'; // Если достигнут конец строки, установка символа EOF
        } else {
            currentChar = input.charAt(position); // Обновление текущего символа
        }
    }

    // Метод для пропуска пробельных символов.
    private void skipWhitespace() {
        while (currentChar != '\0' && Character.isWhitespace(currentChar)) {
            advance(); // Продвижение позиции до тех пор, пока не будет найден не пробельный символ
        }
    }

    // Метод для получения следующего токена из входной строки.
    public Token getNextToken() {
        while (currentChar != '\0') { // Пока не достигнут конец строки
            if (Character.isWhitespace(currentChar)) {
                skipWhitespace();
                continue; // Продолжение сканирования после пропуска пробелов
            }

            if (Character.isDigit(currentChar)) { // Если текущий символ - цифра
                StringBuilder value = new StringBuilder();
                while (Character.isDigit(currentChar)) { // Собираем все цифры в число
                    value.append(currentChar);
                    advance();
                }
                return new Token(TokenType.INTEGER, value.toString()); // Возвращаем токен целого числа
            }

            // Проверки на операторы и возврат соответствующих токенов
            if (currentChar == '+') {
                advance();
                return new Token(TokenType.PLUS, "+");
            }

            if (currentChar == '-') {
                advance();
                return new Token(TokenType.MINUS, "-");
            }

            if (currentChar == '*') {
                advance();
                return new Token(TokenType.MULTIPLY, "*");
            }

            if (currentChar == '/') {
                advance();
                return new Token(TokenType.DIVIDE, "/");
            }

            if (currentChar == '=') {
                advance();
                return new Token(TokenType.EQUALS, "=");
            }
        }

        return new Token(TokenType.EOF, null); // Возврат токена конца файла при достижении конца строки
    }

    // Метод для токенизации всей входной строки.
    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>(); // Список для хранения токенов
        Token token = getNextToken();
        while (token.type != TokenType.EOF) { // Пока не достигнут конец файла
            tokens.add(token); // Добавление токена в список
            token = getNextToken(); // Получение следующего токена
        }
        tokens.add(token); // Добавление токена EOF в список
        return tokens; // Возвращение списка токенов
    }
}
