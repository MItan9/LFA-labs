package org.example.LexerImplementation;

// Класс Token представляет собой токен, полученный в результате лексического анализа.
public class Token {
    TokenType type; // Тип токена, определяющий его роль в анализируемом тексте.
    String value; // Строковое значение токена, содержащее фактические данные.

    // Конструктор класса, инициализирующий новый токен с заданными типом и значением.
    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    // Переопределение метода toString() для возвращения строки, представляющей токен.
    // Это упрощает вывод токена на экран или его логирование.
    @Override
    public String toString() {
        // Форматирует строку таким образом, что она содержит тип и значение токена.
        return String.format("Type: %s, Value: '%s'", type, value);
    }
}
