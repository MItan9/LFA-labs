package org.example.LexerImplementation;

// Token types
public enum TokenType {
    // Перечисление TokenType определяет возможные типы токенов, которые могут быть распознаны лексером.
    INTEGER, PLUS, MINUS, MULTIPLY, DIVIDE, EQUALS, WHITESPACE, EOF
    // Конец файла (или конец ввода), сигнализирует о том, что анализ входных данных завершен
}
