package org.example.LexerImplementation;

// Token class
public class Token {
    TokenType type;
    String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("Type: %s, Value: '%s'", type, value);
    }
}
